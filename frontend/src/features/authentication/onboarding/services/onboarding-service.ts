import { onboardingQuestion } from "../types/question";

const baseUrl = process.env.BASE_API_URL;

export async function getOnboardingQuestions(): Promise<onboardingQuestion[]> {
  const res = await fetch(`${baseUrl}/onboarding`);
  if (!res.ok) {
    throw new Error(`Error ${res.status}: ${res.statusText}`);
  }
  const data = await res.json();
  return data as onboardingQuestion[];
}