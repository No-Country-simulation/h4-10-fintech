"use client"

import { useState } from "react"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import { ArrowUpRight, ArrowDownRight, DollarSign, Percent } from 'lucide-react'

// Datos de ejemplo para las inversiones
const investmentData = {
    cedears: [
        { name: "Apple", ticker: "AAPL", rendimiento: 12.5, plazo: "Largo", riesgo: "Moderado" },
        { name: "Microsoft", ticker: "MSFT", rendimiento: 10.8, plazo: "Largo", riesgo: "Moderado" },
        { name: "Amazon", ticker: "AMZN", rendimiento: 15.2, plazo: "Largo", riesgo: "Alto" },
    ],
    fondos: [
        { name: "Fondo Renta Fija", ticker: "FRF", rendimiento: 5.5, plazo: "Corto", riesgo: "Bajo" },
        { name: "Fondo Acciones", ticker: "FAC", rendimiento: 9.8, plazo: "Largo", riesgo: "Alto" },
        { name: "Fondo Balanceado", ticker: "FBA", rendimiento: 7.2, plazo: "Medio", riesgo: "Moderado" },
    ],
    otros: [
        { name: "Bono Soberano", ticker: "BS2030", rendimiento: 6.5, plazo: "Medio", riesgo: "Moderado" },
        { name: "ETF S&P 500", ticker: "SPY", rendimiento: 8.5, plazo: "Largo", riesgo: "Moderado" },
        { name: "Letra del Tesoro", ticker: "LETE", rendimiento: 4.2, plazo: "Corto", riesgo: "Bajo" },
    ],
}

export function InvestmentManagement() {
    const [selectedCategory, setSelectedCategory] = useState("cedears")
    const [selectedRisk, setSelectedRisk] = useState("all")
    const [selectedPlazo, setSelectedPlazo] = useState("all")

    const filteredInvestments = investmentData[selectedCategory as keyof typeof investmentData].filter(
        (inv) => (selectedRisk === "all" || inv.riesgo === selectedRisk) &&
            (selectedPlazo === "all" || inv.plazo === selectedPlazo)
    )

    return (
        <Card className="bg-white dark:bg-gray-800 shadow-sm">
            <CardHeader>
                <CardTitle className="text-lg font-semibold">Gestión de Inversiones Personalizadas</CardTitle>
            </CardHeader>
            <CardContent>
                <Tabs defaultValue="cedears" onValueChange={setSelectedCategory}>
                    <TabsList className="grid w-full grid-cols-3">
                        <TabsTrigger value="cedears">CEDEARs</TabsTrigger>
                        <TabsTrigger value="fondos">Fondos Comunes</TabsTrigger>
                        <TabsTrigger value="otros">Otros Instrumentos</TabsTrigger>
                    </TabsList>
                    <div className="mt-4 flex space-x-4">
                        <Select onValueChange={setSelectedRisk}>
                            <SelectTrigger className="w-[180px]">
                                <SelectValue placeholder="Filtrar por Riesgo" />
                            </SelectTrigger>
                            <SelectContent>
                                <SelectItem value="all">Todos los Riesgos</SelectItem>
                                <SelectItem value="Bajo">Bajo</SelectItem>
                                <SelectItem value="Moderado">Moderado</SelectItem>
                                <SelectItem value="Alto">Alto</SelectItem>
                            </SelectContent>
                        </Select>
                        <Select onValueChange={setSelectedPlazo}>
                            <SelectTrigger className="w-[180px]">
                                <SelectValue placeholder="Filtrar por Plazo" />
                            </SelectTrigger>
                            <SelectContent>
                                <SelectItem value="all">Todos los Plazos</SelectItem>
                                <SelectItem value="Corto">Corto</SelectItem>
                                <SelectItem value="Medio">Medio</SelectItem>
                                <SelectItem value="Largo">Largo</SelectItem>
                            </SelectContent>
                        </Select>
                    </div>
                    <TabsContent value="cedears" className="mt-4">
                        <InvestmentList investments={filteredInvestments} />
                    </TabsContent>
                    <TabsContent value="fondos" className="mt-4">
                        <InvestmentList investments={filteredInvestments} />
                    </TabsContent>
                    <TabsContent value="otros" className="mt-4">
                        <InvestmentList investments={filteredInvestments} />
                    </TabsContent>
                </Tabs>
            </CardContent>
        </Card>
    )
}

function InvestmentList({ investments }: { investments: any[] }) {
    return (
        <div className="space-y-4">
            {investments.map((inv) => (
                <div key={inv.ticker} className="flex items-center justify-between p-4 bg-gray-100 dark:bg-gray-700 rounded-lg">
                    <div>
                        <h4 className="font-semibold">{inv.name}</h4>
                        <p className="text-sm text-gray-500 dark:text-gray-400">{inv.ticker}</p>
                    </div>
                    <div className="flex items-center space-x-4">
                        <Badge variant={inv.riesgo === "Bajo" ? "secondary" : inv.riesgo === "Moderado" ? "default" : "destructive"}>
                            {inv.riesgo}
                        </Badge>
                        <Badge variant="outline">{inv.plazo}</Badge>
                        <div className="flex items-center">
                            <ArrowUpRight className="h-4 w-4 text-green-500 mr-1" />
                            <span className="font-semibold">{inv.rendimiento}%</span>
                        </div>
                        <Button variant="outline" size="sm">Invertir</Button>
                    </div>
                </div>
            ))}
        </div>
    )
}

