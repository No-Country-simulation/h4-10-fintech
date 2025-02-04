"use client";

import { useState } from "react";
import {
  Card,
  CardContent,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { useAuth } from "@/context/auth-context";

const steps = [
  {
    title: "Conocimiento Financiero",
    question: "¿Cuál es tu nivel de conocimiento financiero?",
    options: ["Principiante", "Intermedio", "Avanzado"],
  },
  {
    title: "Objetivos Principales",
    question: "¿Cuál es tu principal objetivo financiero?",
    options: ["Ahorro", "Inversiones", "Retiro"],
  },
  {
    title: "Preferencia de Riesgo",
    question: "¿Cuál es tu preferencia de riesgo?",
    options: ["Conservador", "Moderado", "Arriesgado"],
  },
  {
    title: "Ingresos Mensuales",
    question: "¿Cuáles son tus ingresos mensuales aproximados?",
    type: "number",
  },
];

export function OnboardingWizard() {
  const [currentStep, setCurrentStep] = useState(0);
  const [answers, setAnswers] = useState<Record<string, string>>({});
  const { user } = useAuth();

  const handleNext = () => {
    if (currentStep < steps.length - 1) {
      setCurrentStep(currentStep + 1);
    } else {
      // Aquí se enviarían las respuestas al backend
      console.log("Respuestas del onboarding:", answers);
      // Redirigir al dashboard
    }
  };

  const handleAnswer = (answer: string) => {
    setAnswers({ ...answers, [steps[currentStep].title]: answer });
  };

  return (
    <Card className="w-[400px]">
      <CardHeader>
        <CardTitle>Bienvenido a Iupi, {user?.name}!</CardTitle>
      </CardHeader>
      <CardContent>
        <h2 className="text-lg font-semibold mb-4">
          {steps[currentStep].question}
        </h2>
        {steps[currentStep].options ? (
          <Select onValueChange={handleAnswer}>
            <SelectTrigger>
              <SelectValue placeholder="Selecciona una opción" />
            </SelectTrigger>
            <SelectContent>
              {steps[currentStep].options.map((option) => (
                <SelectItem key={option} value={option}>
                  {option}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        ) : (
          <Input
            type={steps[currentStep].type || "text"}
            placeholder="Tu respuesta"
            onChange={(e) => handleAnswer(e.target.value)}
          />
        )}
      </CardContent>
      <CardFooter>
        <Button onClick={handleNext} className="w-full">
          {currentStep < steps.length - 1 ? "Siguiente" : "Finalizar"}
        </Button>
      </CardFooter>
    </Card>
  );
}
