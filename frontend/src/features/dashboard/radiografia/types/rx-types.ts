export interface FinancialRx {
  id:                       number;
  edad:                     number;
  personasACargo:           boolean;
  cantPersonasACargo:       number;
  tipoDeTrabajo:            string;
  ingresoMensual:           number;
  tipoIngreso:              string;
  otrosIngresos:            boolean;
  variacionIngreso:         string;
  importeGastosBasicos:     number;
  importeGastosFijos:       number;
  importeGastosRecreativos: number;
  importeGastosEducativos:  number;
  ahorroActual:             number;
  porcentajeAhorro:         number;
  usoHerramientasAhorro:    boolean;
  deudas:                   string;
  porcentajeDeudas:         number;
  deudasOtraMoneda:         boolean;
  inversionActual:          string;
  interesEnAprender:        boolean;
  riesgo:                   string;
  objetivo:                 string;
  recibirRecomendaciones:   boolean;
  userId:                   number;
}
