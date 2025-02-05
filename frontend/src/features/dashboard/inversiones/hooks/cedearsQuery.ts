import { useQuery } from "@tanstack/react-query";
import { getCedears } from "../services/cedearsService";

export function useCedears() {
  const cedearsQuery = useQuery({
    queryKey: ["cedears"],
    queryFn: () => getCedears(),
  });

  return cedearsQuery;
}
