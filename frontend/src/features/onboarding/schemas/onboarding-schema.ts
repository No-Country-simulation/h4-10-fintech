import { z } from "zod";

export const onboardingSchema = z.object({
  "1": z.string(),
  "2": z.string(),
  "3": z.string(),
  "4": z.string(),
  "5": z.string(),
  "6": z.string(),
  respuestaTexto: z.string().min(3).max(30).optional(),
});
