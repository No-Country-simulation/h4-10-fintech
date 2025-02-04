import {useQuery} from "@tanstack/react-query"

export function useUserProfile(userId: string) {
	const userProfileQuery = useQuery({
		queryKey: ["userProfile"],
		queryFn: () => getUserProfile(userId)})
	
	return userProfileQuery;
}