"use client";

import { useState, useEffect } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Progress } from "@/components/ui/progress";
import { Badge } from "@/components/ui/badge";
import { Trophy, Star, Award } from "lucide-react";
import { useAuth } from "@/context/auth-context";

interface Reward {
  id: number;
  name: string;
  description: string;
  icon: React.ElementType;
  progress: number;
}

const rewardsData: Reward[] = [
  {
    id: 1,
    name: "Ahorrador Novato",
    description: "Ahorra tu primer $1000",
    icon: Trophy,
    progress: 75,
  },
  {
    id: 2,
    name: "Inversor Prudente",
    description: "Realiza 5 inversiones",
    icon: Star,
    progress: 40,
  },
  {
    id: 3,
    name: "Maestro Financiero",
    description: "Completa todos los módulos educativos",
    icon: Award,
    progress: 20,
  },
];

export function UserRewards() {
  const { user } = useAuth();
  const [rewards, setRewards] = useState<Reward[]>([]);

  useEffect(() => {
    // En una implementación real, obtendríamos las recompensas del usuario desde el backend
    setRewards(rewardsData);
  }, [user]);

  return (
    <Card className="bg-white dark:bg-gray-800 shadow-sm">
      <CardHeader>
        <CardTitle className="text-lg font-semibold flex items-center">
          <Trophy className="mr-2 h-5 w-5" />
          Tus Recompensas
        </CardTitle>
      </CardHeader>
      <CardContent>
        <div className="space-y-4">
          {rewards.map((reward) => (
            <div key={reward.id} className="space-y-2">
              <div className="flex items-center justify-between">
                <div className="flex items-center space-x-2">
                  <reward.icon className="h-5 w-5 text-yellow-500" />
                  <span className="font-medium">{reward.name}</span>
                </div>
                <Badge variant="secondary">{reward.progress}%</Badge>
              </div>
              <p className="text-sm text-gray-600 dark:text-gray-400">
                {reward.description}
              </p>
              <Progress value={reward.progress} className="h-2" />
            </div>
          ))}
        </div>
      </CardContent>
    </Card>
  );
}
