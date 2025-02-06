import { getUserGoals } from "@/services/local/goals-service";
import { useQuery } from "@tanstack/react-query";

export function useGoals(userId?: number) {
  const goalsQuery = useQuery({
    queryKey: ["localGoals"],
    queryFn: () => getUserGoals(userId),
  });

  return goalsQuery;
}
