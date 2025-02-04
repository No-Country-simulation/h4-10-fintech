import {useQuery} from "@tanStack/react-query"

export function useProducts(id: string) {
	const productsQuery = useQuery({
		queryKey: ["products"],
		queryFn: () => getProducts()})
	
	return productsQuery;
}