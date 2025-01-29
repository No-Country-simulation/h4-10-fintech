"use client";

import OnboardingStep from "@/features/authentication/onboarding/components/OnboardingStep";
import { useOnboardingQuestions } from "@/features/authentication/onboarding/hooks/useOnboardingQuestions";
import testQuestions from "@/features/authentication/onboarding/data/questions";

export default function Onboarding() {
  const {data, isError, isLoading} = useOnboardingQuestions();
  /* if (isError) return (
    <section className="bg-secondary max-w-xl mx-auto text-center">
      <h4>¡Ocurrió un error!</h4>
    </section>
  ); */

  if (true || data) return (
    <section className=" max-w-lg mx-auto text-center">
      <OnboardingStep
        questions={testQuestions}
      />
    </section>
  );
}
