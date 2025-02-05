import { useQuery } from "@tanstack/react-query";
import { getProducts } from "../services/productsService";

export function useProducts() {
  const productsQuery = useQuery({
    queryKey: ["products"],
    queryFn: () => getProducts(),
  });

  return productsQuery;
}
