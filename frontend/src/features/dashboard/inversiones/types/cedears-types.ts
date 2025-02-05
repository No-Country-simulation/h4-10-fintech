export interface CedearsResponse {
  estado:           boolean;
  message:          string;
  data:             Cedear[];
  dataIterable:     Cedear;
  dataListIterable: Cedear[];
}

export interface Cedear {
  horizonteInversion: string;
  ultimoOperado:      number;
  variacion:          number;
  tipoFondo:          string;
  invierte:           string;
  perfilInversor:     string;
  variacionMensual:   number;
  variacionAnual:     number;
  simbolo:            string;
  descripcion:        string;
  pais:               string;
  mercado:            string;
  tipo:               string;
  montoMinimo:        number;
}

