export interface User {
  usuarioId: number;
  auth0Id: string;
  nombre?: string;
  email: string;
  genero?: string;
  pais?: string;
  direccion?: string;
  telefono?: string;
  identificacion?: string;
  fechaNacimiento?: Date;
  fechaRegistro: Date;
  fechaUltimaConexion?: Date;
  estadoRegistro: string;
  tipoIdentificacion?: string;
  perfil: Perfil;
  enabled: boolean;
  accountNonExpired: boolean;
  credentialsNonExpired: boolean;
  accountNonLocked: boolean;
  password?: string;
  username?: string;
}

export interface Perfil {
  perfilId: number;
  nivelEconomico: string;
  capacidadAhorro: string;
  conocimientoFinanciero: string;
  perfilRiesgo: string;
  objetivoPrincipal: string;
  user: number;
}

export interface Transaccion {
  id: number;
  tipoProducto: string;
  monto: number;
  moneda: string;
  tipoTransaccion: string;
  fecha: Date;
  perfil: string;
  tiempo: Tiempo;
  cuenta: Cuenta;
}

export interface Cuenta {
  id: number;
  alias: string;
  numeroCuenta: string;
  monto: number;
  user: string;
  transacciones: string[];
}

export interface Tiempo {
  tiempoId: number;
  fecha: Date;
  anio: number;
  mes: number;
  dia: number;
  trimestre: number;
  semana: number;
}