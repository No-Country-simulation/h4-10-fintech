import { getCustomToken, getUserByEmail } from "@/services/user-service";
import { useUserStore } from "@/store/user-store";
import { useUser } from "@auth0/nextjs-auth0/client";
import { useEffect } from "react";

export function useAuth() {
  const { user } = useUser();
  const iUpiUser = useUserStore((state) => state.iUpiUser);
  const customToken = useUserStore((state) => state.customToken);
  // const setiUpiUser = useUserStore((state) => state.setiUpiUser);
  // const setCustomToken = useUserStore((state) => state.setCustomToken);

  useEffect(() => {
    (async () => {
      if (user && customToken && iUpiUser) {
        return;
      } 
      else if (user && customToken && !iUpiUser) {
      console.log("No había iUpiUser", iUpiUser);
      const newIUpiUser = await getUserByEmail(user.email ?? "");
      console.log(newIUpiUser);
      return;
      }
      else if (user && !customToken) {
        console.log("No había token ni iUpiUser", customToken, iUpiUser);
        const newToken = await getCustomToken();
        const newIUpiUser = await getUserByEmail(user.email ?? "");
        console.log(newToken);
        console.log(newIUpiUser);
        return;
      }
    })();
  });

  return { iUpiUser, customToken };
}
