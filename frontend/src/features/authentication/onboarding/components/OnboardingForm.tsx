import { z } from "zod";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { Label } from "@/components/ui/label";
import { Button } from "@/components/ui/button";

import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { useSteps } from "@/features/authentication/onboarding/hooks/useSteps";
import { onboardingSchema } from "@/features/authentication/onboarding/schemas/onboarding-schema";
import { onboardingQuestion } from "@/features/authentication/onboarding/types/question";

interface onboardingStepProps {
  questions: onboardingQuestion[];
}

export default function OnboardingForm({ questions }: onboardingStepProps) {
  const { step, showNextStep, showPrevStep, updateAnswers } = useSteps({
    totalQuestions: questions.length,
  });
  const onboardingForm = useForm<z.infer<typeof onboardingSchema>>({
    resolver: zodResolver(onboardingSchema),
  });

  return (
    <Form {...onboardingForm}>
      <form className="mx-1" onSubmit={(e) => e.preventDefault()}>
        <h4 className="text-[40px] leading-[1.2] text-balance pt-8">
          {questions[step - 1].pregunta}
        </h4>

        {questions.map((q) => {
          return (
            <FormField
              key={q.id}
              control={onboardingForm.control}
              name={`${String(q.id) as "1" | "2" | "3" | "4" | "5" | "6"}`}
              render={({ field }) => (
                <FormItem className={q.id === step ? "" : "hidden"}>
                  <FormControl>
                    <RadioGroup
                      className="pt-10 pb-20 px-5"
                      defaultValue={field.value}
                      onValueChange={field.onChange}
                      name={String(q.id)}
                      required
                    >
                      {q.opciones.map((option) => (
                        <FormItem
                          className="flex items-start gap-2.5"
                          key={option.idRespuesta}
                        >
                          <FormControl>
                            <RadioGroupItem
                              className="relative top-3"
                              value={String(option.idRespuesta)}
                              id={String(option.idRespuesta)}
                            />
                          </FormControl>
                          <Label
                            htmlFor={String(option.idRespuesta)}
                            className="text-start text-base leading-[1.5] pb-1"
                          >
                            {option.respuesta}
                          </Label>
                        </FormItem>
                      ))}
                    </RadioGroup>
                  </FormControl>
                </FormItem>
              )}
            ></FormField>
          );
        })}

        {step === 4}
        <div className="flex justify-between items-center mx-2">
          <Button
            className="min-w-24 w-1/3 max-w-72"
            variant="secondary"
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
            className="min-w-24 w-1/3 max-w-72"
            type={step === questions.length ? "submit" : "button"}
            onClick={() => {
              showNextStep();
            }}
          >
            {"Continuar"}
          </Button>
        </div>
      </form>
    </Form>
  );
}
