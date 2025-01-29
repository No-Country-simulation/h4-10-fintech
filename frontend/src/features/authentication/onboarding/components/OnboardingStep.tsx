import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { Label } from "@/components/ui/label";
import { Button } from "@/components/ui/button";
import { useSteps } from "@/features/authentication/onboarding/hooks/useSteps";
import { onboardingQuestion } from "@/features/authentication/onboarding/types/question";

interface onboardingStepProps {
  questions: onboardingQuestion[];
}

export default function OnboardingStep({ questions }: onboardingStepProps) {
  const { step, showNextStep, showPrevStep, updateAnswers } = useSteps({
    totalQuestions: questions.length,
  });

  return (
    <form className="mx-1" onSubmit={(e) => e.preventDefault()}>
      <h4 className="text-[40px] leading-[1.2] text-balance pt-8">
        {questions[step - 1].pregunta}
      </h4>
      <RadioGroup defaultValue="" className="pt-10 pb-20 px-5" name={String(step)} required>
        {questions[step - 1].opciones.map((option) => (
          <div className="flex items-start gap-2.5" key={option.idRespuesta}>
            <RadioGroupItem
              className="relative top-1"
              value={String(option.idRespuesta)}
              id={String(option.idRespuesta)}
            />
            <Label
              htmlFor={String(option.idRespuesta)}
              className="text-start text-base leading-[1.5] pb-1"
            >
              {option.respuesta}
            </Label>
          </div>
        ))}
      </RadioGroup>
      {step === 4 }
      <div className="flex justify-between items-center">
        <Button
          className="w-24"
          onClick={() => {
            showPrevStep();
          }}
        >
          Atrás
        </Button>
        <span>
          {step} / {questions.length}
        </span>
        <Button
          className="w-72"
          type="submit"
          onClick={() => {
            showNextStep();
          }}
        >
          {"Continuar"}
        </Button>
      </div>
    </form>
  );
}
