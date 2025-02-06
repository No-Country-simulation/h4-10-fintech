export interface UserProfileResponse {
  estado:           boolean;
  message:          string;
  data:             UserProfile;
  dataIterable:     UserProfile;
  dataListIterable: UserProfile[];
}

export interface UserProfile {
  perfilId:               number;
  nivelEconomico:         string;
  capacidadAhorro:        string;
  conocimientoFinanciero: string;
  perfilRiesgo:           string;
  objetivoPrincipal:      string;
  userId:                 number;
}
