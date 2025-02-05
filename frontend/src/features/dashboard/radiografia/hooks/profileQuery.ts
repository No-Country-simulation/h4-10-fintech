import { useQuery } from "@tanstack/react-query";
import { getUserProfile } from "../services/profileService";

export function useUserProfile(userId: number) {
  const userProfileQuery = useQuery({
    queryKey: ["userProfile"],
    queryFn: () => getUserProfile(userId),
  });

  return userProfileQuery;
}
