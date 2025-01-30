import { useQuery } from "@tanstack/react-query";
import { getOnboardingQuestions } from "../services/onboarding-service";

export function useOnboardingQuestions() {
  const onboardingQuestionsQuery = useQuery({
    queryKey: ["onboarding"],
    queryFn: () => getOnboardingQuestions(),
  });

  return onboardingQuestionsQuery;
}
