import { useState } from "react";
import { onboardingAnswer } from "../types/answers";

interface useStepsArgs {
  totalQuestions: number;
}

export function useSteps({ totalQuestions }: useStepsArgs) {
  const [currentStep, setCurrentStep] = useState(1);
  const [answers, setAnswers] = useState<onboardingAnswer[]>([]);

  function showPrevStep() {
    if (currentStep <= 1) return;
    setCurrentStep(currentStep - 1);
  }

  function showNextStep() {
    if (currentStep >= totalQuestions) return;
    setCurrentStep(currentStep + 1);
  }

  function updateAnswers(userChoice: onboardingAnswer) {
    const newAnswer: onboardingAnswer = {
      idPregunta: currentStep,
      idRespuesta: Number(userChoice),
    };

    const oldAnswerIndex = answers.findIndex(
      (e) => e.idPregunta === currentStep - 1
    );

    if (oldAnswerIndex >= 0) {
      const newAnswers = answers.with(oldAnswerIndex, newAnswer);
      setAnswers(newAnswers);
    } else {
      const newAnswers = [...answers, newAnswer];
      setAnswers(newAnswers);
    }
  }

  return { step: currentStep, showNextStep, showPrevStep, updateAnswers };
}
