"use client"

import { useState } from 'react'
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { TrendingUp, AlertTriangle, Info } from 'lucide-react'
import { useAuth } from '@/app/context/auth-context'

interface Investment {
    id: number;
    name: string;
    risk: string;
    return: string;
    trending: boolean;
    description: string;
}

const allInvestments: Investment[] = [
    { id: 1, name: "Fondo de Inversión A", risk: "Bajo", return: "5-7%", trending: true, description: "Fondo diversificado de renta fija" },
    { id: 2, name: "Acciones Tecnológicas", risk: "Alto", return: "10-15%", trending: false, description: "Cartera de acciones de empresas tech" },
    { id: 3, name: "Bonos Corporativos", risk: "Medio", return: "7-9%", trending: true, description: "Bonos de empresas líderes" },
    { id: 4, name: "ETF S&P 500", risk: "Medio", return: "8-12%", trending: true, description: "Fondo que sigue al índice S&P 500" },
    { id: 5, name: "Fondo Inmobiliario", risk: "Bajo", return: "4-6%", trending: false, description: "Inversión en propiedades comerciales" },
]

export function RecommendedInvestments() {
    const { user } = useAuth()
    const [investments, setInvestments] = useState<Investment[]>([])

    // Simulación de recomendaciones personalizadas
    useState(() => {
        if (user) {
            // Aquí iría la lógica real de recomendación basada en el perfil del usuario
            const recommendedIds = [1, 3, 4] // Ejemplo: recomendamos los IDs 1, 3 y 4
            setInvestments(allInvestments.filter(inv => recommendedIds.includes(inv.id)))
        }
    }, [user])

    return (
        <Card className="bg-white dark:bg-gray-800 shadow-sm">
            <CardHeader>
                <CardTitle className="text-lg font-semibold">Inversiones Recomendadas</CardTitle>
            </CardHeader>
            <CardContent>
                <div className="space-y-4">
                    {investments.map((investment) => (
                        <div key={investment.id} className="flex flex-col space-y-2 p-4 bg-gray-100 dark:bg-gray-700 rounded-lg">
                            <div className="flex justify-between items-center">
                                <h4 className="font-semibold">{investment.name}</h4>
                                {investment.trending && (
                                    <Badge variant="secondary">
                                        <TrendingUp className="h-3 w-3 mr-1" />
                                        Tendencia
                                    </Badge>
                                )}
                            </div>
                            <p className="text-sm text-gray-600 dark:text-gray-300">{investment.description}</p>
                            <div className="flex items-center space-x-2 text-sm text-gray-500 dark:text-gray-400">
                                <AlertTriangle className="h-4 w-4" />
                                <span>Riesgo: {investment.risk}</span>
                            </div>
                            <p className="text-sm font-medium">Retorno esperado: {investment.return}</p>
                            <Button variant="outline" className="w-full mt-2">Invertir</Button>
                        </div>
                    ))}
                </div>
                <div className="mt-4 p-4 bg-blue-100 dark:bg-blue-900 rounded-lg">
                    <div className="flex items-center space-x-2">
                        <Info className="h-5 w-5 text-blue-500" />
                        <p className="text-sm text-blue-700 dark:text-blue-300">
                            Estas recomendaciones están basadas en tu perfil de riesgo y objetivos financieros.
                        </p>
                    </div>
                </div>
            </CardContent>
        </Card>
    )
}

