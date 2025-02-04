"use client"

import { useState } from "react"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Loader2 } from 'lucide-react'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"

export function FinancialRadiography() {
    const [isOpen, setIsOpen] = useState(false)
    const [isLoading, setIsLoading] = useState(false)
    const [isComplete, setIsComplete] = useState(false)

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault()
        setIsLoading(true)
        // Simular envío de datos
        setTimeout(() => {
            setIsLoading(false)
            setIsComplete(true)
            setIsOpen(false)
        }, 2000)
    }

    return (
        <Card className="bg-white dark:bg-gray-800 shadow-sm">
            <CardHeader>
                <CardTitle className="text-lg font-semibold">Radiografía Financiera</CardTitle>
            </CardHeader>
            <CardContent>
                {!isOpen ? (
                    <div className="space-y-4">
                        <Button onClick={() => setIsOpen(true)} className="w-full">
                            {isComplete ? "Actualizar Radiografía Financiera" : "Realizar Radiografía Financiera"}
                        </Button>
                        {isComplete && (
                            <p className="text-sm text-green-600 dark:text-green-400">
                                ¡Radiografía completa! Ahora recibirás recomendaciones personalizadas en la sección de inversiones.
                            </p>
                        )}
                    </div>
                ) : (
                    <form onSubmit={handleSubmit} className="space-y-4">
                        <div>
                            <Label htmlFor="income">Ingresos Mensuales</Label>
                            <Input id="income" type="number" placeholder="Ingrese sus ingresos mensuales" />
                        </div>
                        <div>
                            <Label htmlFor="expenses">Gastos Fijos Mensuales</Label>
                            <Input id="expenses" type="number" placeholder="Ingrese sus gastos fijos mensuales" />
                        </div>
                        <div>
                            <Label htmlFor="debts">Deudas Totales</Label>
                            <Input id="debts" type="number" placeholder="Ingrese el total de sus deudas" />
                        </div>
                        <div>
                            <Label htmlFor="savings">Ahorros Actuales</Label>
                            <Input id="savings" type="number" placeholder="Ingrese sus ahorros actuales" />
                        </div>
                        <div>
                            <Label htmlFor="risk-profile">Perfil de Riesgo</Label>
                            <Select>
                                <SelectTrigger id="risk-profile">
                                    <SelectValue placeholder="Seleccione su perfil de riesgo" />
                                </SelectTrigger>
                                <SelectContent>
                                    <SelectItem value="conservador">Conservador</SelectItem>
                                    <SelectItem value="moderado">Moderado</SelectItem>
                                    <SelectItem value="agresivo">Agresivo</SelectItem>
                                </SelectContent>
                            </Select>
                        </div>
                        <Button type="submit" className="w-full" disabled={isLoading}>
                            {isLoading ? (
                                <>
                                    <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                                    Procesando...
                                </>
                            ) : (
                                "Guardar Información"
                            )}
                        </Button>
                    </form>
                )}
            </CardContent>
        </Card>
    )
}

