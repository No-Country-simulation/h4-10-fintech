# IUPI_REC

## API de Recomendaciones FCI

Este proyecto expone una API de recomendaciones de productos FCI usando **FastAPI** y **PostgreSQL**. El objetivo es proporcionar un endpoint que, dado el perfil de un usuario, devuelva los productos financieros más adecuados para su perfil de inversión.

---

## Requisitos Previos

- **Python 3.8+**
- **PostgreSQL** (local o remoto, como Render)
- **Entorno Virtual** (recomendado)

---

## Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/leocortes85/IUPI_REC.git
cd IUPI_REC
```

### 2. Crear y activar un entorno virtual (opcional pero recomendado)

```bash
# En Linux o Mac
python -m venv venv
source venv/bin/activate

# En Windows
python -m venv venv
venv\Scripts\activate
```

### 3. Instalar las dependencias

```bash
pip install -r requirements.txt
```

### 4. Configurar las Variables de Entorno para PostgreSQL

#### En Linux/Mac:

```bash
export DB_HOST=your-db-host.render.com
export DB_PORT=5432
export DB_NAME=nombre_db
export DB_USER=usuario_db
export DB_PASS=password_db
```

#### En Windows PowerShell:

```powershell
$env:DB_HOST="your-db-host.render.com"
$env:DB_PORT="5432"
$env:DB_NAME="nombre_db"
$env:DB_USER="usuario_db"
$env:DB_PASS="password_db"
```

### 5. Iniciar la aplicación con Uvicorn

```bash
uvicorn main:app --host 0.0.0.0 --port 8000
```

Esto levantará el servidor de FastAPI en [http://localhost:8000](http://localhost:8000).

---

## Uso de la API

### Ruta Raíz

- **GET /**
- Devuelve un mensaje de bienvenida para verificar que la API está en funcionamiento.

#### Ejemplo de respuesta:

```json
{
  "message": "Hola desde la API con credenciales seguras"
}
```

### Endpoint de Recomendaciones

- **POST /recomendar**
- Recibe un JSON con la identificación de un usuario y retorna hasta 3 productos recomendados.

#### Ejemplo de solicitud:

```json
{
  "identificacion": "123456"
}
```

#### Ejemplo de respuesta:

```json
[
  {
    "id_productosfci": 1,
    "prob": 0.85,
    "descripcion": "Fondo Conservador",
    "horizonte_inversion": "Corto Plazo",
    "instrumentos": "Bonos",
    "perfil_inversor": "Conservador",
    "tipo_de_fondo": "Renta Fija"
  }
]
```

Si no existen recomendaciones para esa identificación, se devuelve un **404 Not Found**.

---

## Arquitectura

Este proyecto combina un modelo de **RandomForestClassifier** con un esquema relacional que obtiene datos de varias tablas:

- **usuario**
- **perfiles**
- **cuentas**
- **transacciones**
- **productosfci**

Se realizan **JOINs internos** para unificar la información y entrenar un modelo que asocia el perfil de cada usuario con los productos FCI que ha adquirido.

---

## Despliegue en Render

### Pasos para crear un nuevo servicio:

1. Sube este repositorio a **GitHub** (o **GitLab**).
2. Crea un **New Web Service** en [Render](https://render.com/) y enlaza el repositorio.
3. En la sección **Environment**, añade las variables de entorno.
4. En el campo **Start Command**, ingresa:

   ```bash
   uvicorn fastapi_recomendaciones:app --host 0.0.0.0 --port 10000
   ```

Render te asignará una URL pública, por ejemplo:

```
https://mi-fastapi.onrender.com
```

Desde tu portal web (Java, Python, etc.) puedes consumir el endpoint:

```
POST https://mi-fastapi.onrender.com/recomendar
```

---

## Mantenimiento y Ajustes

- Ajusta la consulta SQL y las columnas según la estructura real de tus tablas.
- Define variables de entorno adicionales (por ejemplo, para modo **debug**) si es necesario.
- Asegura que tu modelo de Machine Learning (RandomForest en este ejemplo) represente la lógica real de tu caso, entrenándolo con la variable objetivo adecuada.
