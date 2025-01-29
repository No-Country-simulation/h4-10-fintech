"use client"

import React, { createContext, useContext, useState, useEffect } from 'react';
import { User } from '@/lib/use-roles';

interface AuthContextType {
    user: User | null;
    login: (email: string, password: string) => Promise<void>;
    logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [user, setUser] = useState<User | null>(null);

    useEffect(() => {
        // Aquí normalmente verificarías si hay una sesión activa
        // Por ahora, simularemos un usuario logueado
        setUser({
            id: '1',
            name: 'Usuario Ejemplo',
            email: 'usuario@ejemplo.com',
            role: 'standard'
        });
    }, []);

    const login = async (email: string, password: string) => {
        // Aquí iría la lógica real de autenticación
        setUser({
            id: '1',
            name: 'Usuario Ejemplo',
            email: email,
            role: email.includes('admin') ? 'admin' : 'standard'
        });
    };

    const logout = () => {
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (context === undefined) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};

