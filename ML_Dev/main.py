# archivo: fastapi_recomendaciones.py
# Created/Modified files during execution: None

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List, Any
from sqlalchemy import create_engine, text
import pandas as pd
import numpy as np
import os

from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split

# --------------------------------------------------------------------------------
# 1) Configuración y conexión a la Base de Datos en Render
# --------------------------------------------------------------------------------

app = FastAPI(
    title="API de Recomendaciones FCI",
    description="Expone endpoints para recomendar productos FCI en base al perfil del usuario.",
    version="1.0.0"
)

DB_HOST = os.getenv("DB_HOST", "localhost")
DB_PORT = os.getenv("DB_PORT", "5432")
DB_NAME = os.getenv("DB_NAME", "postgres")
DB_USER = os.getenv("DB_USER", "postgres")
DB_PASS = os.getenv("DB_PASS", "password")

DB_URL = f"postgresql://{DB_USER}:{DB_PASS}@{DB_HOST}:{DB_PORT}/{DB_NAME}"

engine = create_engine(DB_URL)

@app.get("/")
def index():
    return {"message": "Hola desde la API con credenciales seguras"}




# --------------------------------------------------------------------------------
# 2) Definir Modelos Pydantic (Request/Response)
# --------------------------------------------------------------------------------

class Recomendacion(BaseModel):
    id_productosfci: Any
    prob: float
    descripcion: str
    horizonte_inversion: str
    instrumentos: str
    perfil_inversor: str
    tipo_de_fondo: str

class RequestRecomendacion(BaseModel):
    identificacion: str  # Valor de entrada: ID del usuario

# --------------------------------------------------------------------------------
# 3) Cargar / Unir datos y Entrenar el Modelo en el arranque
# --------------------------------------------------------------------------------

def cargar_datos_unidos():
    """
    Realiza los JOINs:
      usuario -> perfiles        (por usuario_id)
      usuario -> cuentas         (por usuario_id)
      cuentas -> transacciones   (por cuenta_id)
      transacciones -> productosfci (por productofci_id -> id_productosfci)

    Retorna un DataFrame con las columnas clave:
       - usuario.identificacion
       - perfiles: nivel_economico, capacidad_ahorro, conocimiento_financiero, perfil_riesgo
       - productosfci: id_productosfci
    """
    query = text("""
        SELECT
            u.identificacion AS usuario_identificacion,
            p.nivel_economico,
            p.capacidad_ahorro,
            p.conocimiento_financiero,
            p.perfil_riesgo,
            fci.id AS id_productosfci
        FROM usuario u
        JOIN perfiles p ON u.usuario_id = p.usuario_id
        JOIN cuentas c ON u.usuario_id = c.usuario_id
        JOIN transacciones t ON c.cuenta_id = t.cuenta_id
        JOIN productosfci fci ON t.productofci_id = fci.id
    """)

    df = pd.read_sql_query(query, con=engine)
    return df

def entrenar_modelo(df_unido: pd.DataFrame) -> RandomForestClassifier:
    """
    Entrena un sencillo RandomForestClassifier (ejemplo) usando
    las columnas de perfiles como 'features' y genera un y sintético.
    En tu caso, reemplaza la lógica de y por tu variable objetivo real.
    """
    feature_cols = [
        "nivel_economico",
        "capacidad_ahorro",
        "conocimiento_financiero",
        "perfil_riesgo"
    ]

    # Convertimos las columnas categóricas a códigos para el modelo
    for col in feature_cols:
        df_unido[col] = df_unido[col].astype("category").cat.codes

    # Generamos un objetivo ficticio "jubilacion"
    np.random.seed(42)
    df_unido["jubilacion"] = np.random.randint(0, 2, size=len(df_unido))

    X = df_unido[feature_cols]
    y = df_unido["jubilacion"]

    X_train, X_test, y_train, y_test = train_test_split(X, y, random_state=42)
    model = RandomForestClassifier(n_estimators=100, random_state=42)
    model.fit(X_train, y_train)

    acc = model.score(X_test, y_test)
    print(f"[INFO] Exactitud modelo (dummy): {acc:0.4f}")

    return model

# --------------------------------------------------------------------------------
# 4) Inicialización (cargamos datos, entrenamos el modelo al iniciar la app)
# --------------------------------------------------------------------------------

df_unido_global = cargar_datos_unidos()
modelo_global = entrenar_modelo(df_unido_global)

# --------------------------------------------------------------------------------
# 5) Función de recomendación
# --------------------------------------------------------------------------------

def recomendar_productos(identificacion: str) -> List[Recomendacion]:
    """
    1. Filtrar filas de df_unido_global donde usuario_identificacion = ...
    2. Generar DataFrame con (usuario + ID producto FCI), aplicamos predict_proba
    3. Ordenamos por prob desc y tomamos los top 3
    4. Retornamos las columnas (descripcion, horizonte_inversion, instrumentos, perfil_inversor, tipo_de_fondo)
    """

    df_user = df_unido_global[df_unido_global["usuario_identificacion"] == identificacion].copy()
    if df_user.empty:
        # Si no encontró filas, retornamos lista vacía o lanzamos excepción
        return []

    # Extraemos features en el df_user
    feature_cols = ["nivel_economico", "capacidad_ahorro", "conocimiento_financiero", "perfil_riesgo"]

    # Tomamos los cat.codes.
    # Asegúrate de haber guardado los mapeos originales si en producción cambian.
    for col in feature_cols:
        df_user[col] = df_user[col].astype("category").cat.codes

    # Obtenemos la lista de productos fci que el usuario tiene en transacciones
    productos_ids = df_user["id_productosfci"].unique()

    # Creamos un mini DataFrame para predecir la prob
    data_predict = []
    # Tomamos la primera fila del DF user como "perfil"
    first_row = df_user.iloc[0]
    for pid in productos_ids:
        row = {
            "id_productosfci": pid,
            "nivel_economico": first_row["nivel_economico"],
            "capacidad_ahorro": first_row["capacidad_ahorro"],
            "conocimiento_financiero": first_row["conocimiento_financiero"],
            "perfil_riesgo": first_row["perfil_riesgo"],
        }
        data_predict.append(row)

    df_predict = pd.DataFrame(data_predict)

    X_infer = df_predict[feature_cols]
    proba = modelo_global.predict_proba(X_infer)[:, 1]  # prob de adopción
    df_predict["prob"] = proba

    # Ordenamos descendente y cogemos top 3
    df_top = df_predict.sort_values(by="prob", ascending=False).head(3)

    # Buscamos las columnas solicitadas en productosfci
    # (descripcion, horizonte_inversion, invierte AS instrumentos, perfil_inversor, tipo_de_fondo)
    ids_str = ",".join(map(str, df_top["id_productosfci"].tolist()))
    if not ids_str:
        return []

    query_fci = text(f"""
        SELECT
            id AS id_productosfci,
            descripcion,
            horizonte_inversion,
            invierte AS instrumentos,
            perfil_inversor,
            tipo_fondo
        FROM productosfci
        WHERE id IN ({ids_str});
    """)
    df_fci = pd.read_sql_query(query_fci, con=engine)

    # Unimos la info (por p.id_productosfci)
    df_final = pd.merge(df_top, df_fci, on="id_productosfci", how="left")

    # Convertimos a lista de Recomendacion
    recomendaciones = []
    for _, row in df_final.iterrows():
        recomendaciones.append(
            Recomendacion(
                id_productosfci=row["id_productosfci"],
                prob=float(row["prob"]),
                descripcion=row["descripcion"],
                horizonte_inversion=row["horizonte_inversion"],
                instrumentos=row["instrumentos"],
                perfil_inversor=row["perfil_inversor"],
                tipo_de_fondo=row["tipo_fondo"]
            )
        )
    return recomendaciones

# --------------------------------------------------------------------------------
# 6) Definir el Endpoint
# --------------------------------------------------------------------------------

@app.post("/recomendar", response_model=List[Recomendacion])
def recomendar(data: RequestRecomendacion):
    """
    Recibe la identificación del usuario y devuelve una lista
    con los Top 3 productos recomendados.
    """
    recomendaciones = recomendar_productos(data.identificacion)
    if not recomendaciones:
        # Si está vacío, lanzamos un 404 (o simplemente retornamos lista vacía)
        raise HTTPException(
            status_code=404,
            detail=f"No se encontraron recomendaciones para el usuario {data.identificacion}"
        )
    return recomendaciones