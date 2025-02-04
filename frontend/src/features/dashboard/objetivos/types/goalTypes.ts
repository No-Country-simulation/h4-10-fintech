export interface GoalsResponse {
  estado:           boolean;
  message:          string;
  data:             Goal[];
  dataIterable:     Goal;
  dataListIterable: Goal[];
}

export interface Goal {
  id:             number;
  nombreObjetivo: string;
  montoTotal:     number;
  montoActual:    number;
}
