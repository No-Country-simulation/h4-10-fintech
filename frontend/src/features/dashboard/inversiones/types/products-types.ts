export interface ProductsResponse {
  estado:           boolean;
  message:          string;
  data:             Product[];
  dataIterable:     Product;
  dataListIterable: Product[];
}

export interface Product {
  id:                      number;
  categoria:               string;
  nombre:                  string;
  descripcion:             string;
  moneda:                  string;
  tasaInteres:             number;
  plazoMinimo:             number;
  tipoProducto:            string;
  tasaRendimientoEsperada: number;
  riesgo:                  string;
  entidadProveedor:        string;
}
