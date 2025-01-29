"use client"

import { useState } from 'react'
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { PieChart, Pie, Cell, ResponsiveContainer, Legend, Tooltip } from 'recharts'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042', '#8884D8']

const initialData = [
    { name: 'Vivienda', value: 35 },
    { name: 'Alimentación', value: 25 },
    { name: 'Transporte', value: 15 },
    { name: 'Entretenimiento', value: 10 },
    { name: 'Otros', value: 15 },
]

export function ExpenseAnalysis() {
    const [data, setData] = useState(initialData)
    const [period, setPeriod] = useState('mes')

    // En una implementación real, esta función obtendría datos del backend
    const updateData = (selectedPeriod: string) => {
        setPeriod(selectedPeriod)
        // Aquí simularemos un cambio en los datos
        if (selectedPeriod === 'año') {
            setData([
                { name: 'Vivienda', value: 40 },
                { name: 'Alimentación', value: 20 },
                { name: 'Transporte', value: 15 },
                { name: 'Entretenimiento', value: 15 },
                { name: 'Otros', value: 10 },
            ])
        } else {
            setData(initialData)
        }
    }

    return (
        <Card className="bg-white dark:bg-gray-800 shadow-sm">
            <CardHeader>
                <CardTitle className="text-lg font-semibold">Análisis de Gastos</CardTitle>
            </CardHeader>
            <CardContent>
                <div className="mb-4">
                    <Select value={period} onValueChange={updateData}>
                        <SelectTrigger>
                            <SelectValue placeholder="Seleccionar período" />
                        </SelectTrigger>
                        <SelectContent>
                            <SelectItem value="mes">Este mes</SelectItem>
                            <SelectItem value="año">Este año</SelectItem>
                        </SelectContent>
                    </Select>
                </div>
                <div className="h-[300px]">
                    <ResponsiveContainer width="100%" height="100%">
                        <PieChart>
                            <Pie
                                data={data}
                                cx="50%"
                                cy="50%"
                                labelLine={false}
                                outerRadius={80}
                                fill="#8884d8"
                                dataKey="value"
                                label={({ name, percent }) => `${name} ${(percent * 100).toFixed(0)}%`}
                            >
                                {data.map((entry, index) => (
                                    <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                                ))}
                            </Pie>
                            <Tooltip />
                            <Legend />
                        </PieChart>
                    </ResponsiveContainer>
                </div>
            </CardContent>
        </Card>
    )
}

