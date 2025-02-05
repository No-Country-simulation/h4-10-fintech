"use client";

import React, { createContext, useContext, useState, useEffect } from "react";
import { useUser } from "@auth0/nextjs-auth0/client";
import { getUserByEmail } from "@/services/user-service";
import { User } from "@/types/user";

interface AuthContextType {
  user?: User;
}

const AuthContext = createContext<AuthContextType>({user: undefined});

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const { user } = useUser();
  const [usuario, setUsuario] = useState<User | undefined>();

  useEffect(() => {
    // Aquí normalmente verificarías si hay una sesión activa
    // Por ahora, simularemos un usuario logueado
    if (user) {
      (async () => {
        const userData = await getUserByEmail(user.email ?? "");
        setUsuario(userData);
      })();
    } else {
      setUsuario(undefined);
    }
  }, [user]);

  return (
    <AuthContext.Provider value={{ user: usuario }}>
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
