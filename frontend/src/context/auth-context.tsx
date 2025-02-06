/* "use client";

import React, { createContext, useContext, useEffect } from "react";
import { useUser } from "@auth0/nextjs-auth0/client";
import { getUserByEmail } from "@/services/user-service";
import { User } from "@/types/user";
import { useUserStore } from "@/store/token-store";
import { redirect } from "next/navigation";

interface AuthContextType {
  user?: User;
  customToken: string,
}

const AuthContext = createContext<AuthContextType>({user: undefined, customToken: ""});

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const { user } = useUser();
  const iUpiUser = useUserStore(state => state.iUpiUser);
  const customToken = useUserStore(state => state.customToken);
  const setiUpiUser = useUserStore((state) => state.setiUpiUser);
  const setCustomToken = useUserStore((state) => state.setCustomToken);

  useEffect(() => {
    if(!user) return redirect("/");
    if (user) {
      (async () => {
        const userData = await getUserByEmail(user.email ?? "");
        setiUpiUser(userData);
      })();
    } else {
      setiUpiUser(undefined);
    }
  }, [user]);

  return (
    <AuthContext.Provider value={{ user: iUpiUser }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context || !context.user) {
    console.log("Usuario no loggeado!");
  }
  return context;
};
 */