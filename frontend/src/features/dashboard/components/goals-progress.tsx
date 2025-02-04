"use client";

import { useState } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Progress } from "@/components/ui/progress";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Plane, Car, Home, GraduationCap, Trophy, Plus } from "lucide-react";

const goalIcons: { [key: string]: React.ElementType } = {
  Vacaciones: Plane,
  Auto: Car,
  Casa: Home,
  Educación: GraduationCap,
};

interface Goal {
  id: number;
  name: string;
  icon: string;
  target: number;
  current: number;
}

export function GoalsProgress() {
  const [goals, setGoals] = useState<Goal[]>([
    {
      id: 1,
      name: "Vacaciones 2025",
      icon: "Vacaciones",
      target: 5000,
      current: 3000,
    },
    {
      id: 2,
      name: "Comprar un auto",
      icon: "Auto",
      target: 20000,
      current: 6000,
    },
  ]);
  const [newGoal, setNewGoal] = useState({ name: "", target: "" });

  const addGoal = () => {
    if (newGoal.name && newGoal.target) {
      setGoals([
        ...goals,
        {
          id: goals.length + 1,
          name: newGoal.name,
          icon: Object.keys(goalIcons)[0],
          target: parseInt(newGoal.target),
          current: 0,
        },
      ]);
      setNewGoal({ name: "", target: "" });
    }
  };

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
            const progress = Math.round((goal.current / goal.target) * 100);
            const Icon = goalIcons[goal.icon] || Trophy;
            return (
              <div key={goal.id} className="space-y-2">
                <div className="flex items-center justify-between">
                  <div className="flex items-center space-x-2">
                    <Icon className="h-5 w-5 text-blue-500" />
                    <span>{goal.name}</span>
                  </div>
                  <div className="flex items-center space-x-2">
                    <Badge variant="secondary">{progress}%</Badge>
                    <Trophy
                      className={`h-5 w-5 ${
                        progress >= 100 ? "text-yellow-500" : "text-gray-300"
                      }`}
                    />
                  </div>
                </div>
                <Progress value={progress} className="h-2" />
                <p className="text-sm text-gray-600 dark:text-gray-400">
                  ${goal.current} / ${goal.target}
                </p>
              </div>
            );
          })}
        </div>
        <div className="mt-6 space-y-2">
          <Input
            placeholder="Nombre del objetivo"
            value={newGoal.name}
            onChange={(e) => setNewGoal({ ...newGoal, name: e.target.value })}
          />
          <Input
            type="number"
            placeholder="Monto objetivo"
            value={newGoal.target}
            onChange={(e) => setNewGoal({ ...newGoal, target: e.target.value })}
          />
          <Button onClick={addGoal} className="w-full">
            <Plus className="h-4 w-4 mr-2" /> Agregar Objetivo
          </Button>
        </div>
      </CardContent>
    </Card>
  );
}
