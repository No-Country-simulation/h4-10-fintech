"use client";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { useState } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Progress } from "@/components/ui/progress";
import { Badge } from "@/components/ui/badge";
import { Trophy, Plus } from "lucide-react";
import { Goal } from "../objetivos/types/goalTypes";
import { postGoal } from "../objetivos/services/goalsService";

export function GoalsProgress() {
  const goals = [
    {
      id: 1,
      nombreObjetivo: "Vacaciones 2025",
      montoTotal: 5000,
      montoActual: 3000,
    },
    {
      id: 2,
      nombreObjetivo: "Comprar un auto",
      montoTotal: 20000,
      montoActual: 6000,
    },
  ];
  const [newGoal, setNewGoal] = useState({
    nombreObjetivo: "",
    montoTotal: "",
  });

  async function handleAddGoal(newGoal: {nombreObjetivo: string, montoTotal: string}) {
    const typedAmount = Number(newGoal.montoTotal);
    const typedGoal: Omit<Goal, "id" | "montoActual"> = {nombreObjetivo: newGoal.nombreObjetivo, montoTotal: typedAmount};
    const postedGoal = await postGoal(typedGoal);
    setNewGoal({ nombreObjetivo: "", montoTotal: "" });
    console.log(postedGoal);
  }

  return (
    <Card className="bg-card dark:bg-gray-800 shadow-sm">
      <CardHeader>
        <CardTitle className="text-lg font-semibold">
          Progreso de Objetivos
        </CardTitle>
      </CardHeader>
      <CardContent>
        <div className="space-y-6">
          {goals.map((goal) => {
            const progress = Math.round((goal.montoActual / goal.montoTotal) * 100);
            return (
              <div key={goal.id} className="space-y-2">
                <div className="flex items-center justify-between">
                  <div className="flex items-center space-x-2">
                    {progress === 100 && <Trophy className="h-5 w-5 text-blue-500" />}
                    <span>{goal.nombreObjetivo}</span>
                  </div>
                  <div className="flex items-center space-x-2">
                    <Badge variant="secondary">{progress}%</Badge>
                  </div>
                </div>
                <Progress value={progress} className="h-2" />
                <p className="text-sm text-gray-600 dark:text-gray-400">
                  ${goal.montoActual} / ${goal.montoTotal}
                </p>
              </div>
            );
          })}
        </div>
        <div className="mt-6 space-y-2">
          <Input
            placeholder="Nombre del objetivo"
            value={newGoal.nombreObjetivo}
            onChange={(e) => setNewGoal({ ...newGoal, nombreObjetivo: e.target.value })}
          />
          <Input
            type="number"
            min={0}
            placeholder="Monto objetivo"
            value={newGoal.montoTotal}
            onChange={(e) => setNewGoal({ ...newGoal, montoTotal: e.target.value })}
          />
          <Button
          disabled={!newGoal.nombreObjetivo || Number(newGoal.montoTotal) < 1}
          onClick={() => handleAddGoal(newGoal)} className="w-full">
            <Plus className="h-4 w-4 mr-2" /> Agregar Objetivo
          </Button>
        </div>
      </CardContent>
    </Card>
  );
}
