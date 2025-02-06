"use client";

import OnboardingForm from "@/features/onboarding/components/OnboardingForm";
import { useOnboardingQuestions } from "@/features/dashboard/radiografia/hooks/useOnboardingQuestions";
import testQuestions from "@/data/questions";

export default function Onboarding() {
  const { data /* isError, isLoading */ } = useOnboardingQuestions();
  /* if (isError) return (
    <section className="bg-secondary max-w-xl mx-auto text-center">
      <h4>¡Ocurrió un error!</h4>
    </section>
  ); */

  if (true || data)
    return (
      <section className=" max-w-lg mx-auto text-center">
        <OnboardingForm questions={testQuestions} />
      </section>
    );
}
