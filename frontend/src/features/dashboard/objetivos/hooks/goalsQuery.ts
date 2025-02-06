import { useQuery } from "@tanstack/react-query";
import { getUserGoals } from "../services/goalsService";

export function useGoals() {
  const goalsQuery = useQuery({
    queryKey: ["goals"],
    queryFn: () => getUserGoals(),
  });

  return goalsQuery;
}
