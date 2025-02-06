import { OnboardingAnswer } from "../types/answers";
import { OnboardingQuestion } from "../types/question";
import { FinancialRx } from "../types/rx-types";

const baseUrl = `${process.env.NEXT_PUBLIC_BASE_API_URL}/perfil`;

export async function getUserProfile(userId: number) {
  const res = await fetch(`${baseUrl}/${userId}`);
  const data = await res.json();
  return data;
}

export async function postUserProfile(
  userId: string,
  onboardingAnswers: OnboardingAnswer[]
) {
  const options: RequestInit = {
    method: "POST",
    body: JSON.stringify(onboardingAnswers),
  };
  const res = await fetch(`${baseUrl}/preguntas/${userId}`, options);
  const data = await res.json();
  return data;
}

export async function getOnboardingQuestions(): Promise<OnboardingQuestion[]> {
  const res = await fetch(`${baseUrl}/preguntas`);
  const data = await res.json();
  return data as OnboardingQuestion[];
}

export async function postUserRx(userId: number, rxAnswers: FinancialRx) {
  const options: RequestInit = {
    method: "POST",
    body: JSON.stringify(rxAnswers),
  };
  const res = await fetch(`${baseUrl}/rxfinanciera/${userId}`, options);
  const data = await res.json();
  return data;
}