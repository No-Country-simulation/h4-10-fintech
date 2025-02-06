export interface OnboardingQuestion {
  id: number,
  pregunta: string,
  opciones: Option[],
}

interface Option {
  idRespuesta: number,
  respuesta: string,
}