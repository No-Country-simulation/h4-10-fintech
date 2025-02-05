import { useQuery } from "@tanstack/react-query";
import { getOnboardingQuestions } from "../services/profileService";

export function useOnboardingQuestions() {
  const onboardingQuestionsQuery = useQuery({
    queryKey: ["onboarding"],
    queryFn: () => getOnboardingQuestions(),
  });

  return onboardingQuestionsQuery;
}
