const baseUrl = `${process.env.NEXT_PUBLIC_BASE_API_URL}/perfil`;

export async function getUserProfile(userId: string) {
  const res = await fetch(`${baseUrl}/${userId}`);
  const data = await res.json();
  return data.data;
}

export async function postUserProfile(
  userId: string,
  onboardingAnswers: OnboardingAnswer[]
) {
  const options: RequestInit = {
    headers: {
      method: "POST",
    },
    body: JSON.stringify(onboardingAnswers),
  };
  const res = await fetch(`${baseUrl}/preguntas/${userId}`, options);
  const data = await res.json();
  return data.data;
}

export async function getOnboardingQuestions(): Promise<onboardingQuestion[]> {
  const res = await fetch(`${baseUrl}/onboarding`);
  if (!res.ok) {
    throw new Error(`Error ${res.status}: ${res.statusText}`);
  }
  const data = await res.json();
  return data as onboardingQuestion[];
}
