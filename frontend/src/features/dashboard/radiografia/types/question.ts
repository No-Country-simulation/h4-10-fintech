export interface onboardingQuestion {
  id: number,
  pregunta: string,
  opciones: option[],
}

interface option {
  idRespuesta: number,
  respuesta: string,
}