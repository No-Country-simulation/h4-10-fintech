"use client"

import { useState } from 'react'
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts'

export function InvestmentSimulator() {
    const [initialAmount, setInitialAmount] = useState(1000)
    const [monthlyContribution, setMonthlyContribution] = useState(100)
    const [years, setYears] = useState(5)
    const [interestRate, setInterestRate] = useState(8)
    const [data, setData] = useState<{ year: number; amount: number }[]>([])

    const calculateInvestment = () => {
        let totalAmount = initialAmount
        const newData = []

        for (let year = 1; year <= years; year++) {
            totalAmount = totalAmount * (1 + interestRate / 100) + monthlyContribution * 12
            newData.push({
                year,
                amount: Math.round(totalAmount)
            })
        }

        setData(newData)
    }

    return (
        <Card className="bg-white dark:bg-gray-800 shadow-sm">
            <CardHeader>
                <CardTitle className="text-lg font-semibold">Simulador de Inversiones</CardTitle>
            </CardHeader>
            <CardContent>
                <div className="space-y-4 mb-6">
                    <div className="flex items-center space-x-4">
                        <Input
                            type="number"
                            value={initialAmount}
                            onChange={(e) => setInitialAmount(Number(e.target.value))}
                            placeholder="Monto inicial"
                        />
                        <Input
                            type="number"
                            value={monthlyContribution}
                            onChange={(e) => setMonthlyContribution(Number(e.target.value))}
                            placeholder="Aporte mensual"
                        />
                    </div>
                    <div className="flex items-center space-x-4">
                        <Select value={years.toString()} onValueChange={(value) => setYears(Number(value))}>
                            <SelectTrigger>
                                <SelectValue placeholder="Años" />
                            </SelectTrigger>
                            <SelectContent>
                                {[1, 3, 5, 10, 15, 20].map((year) => (
                                    <SelectItem key={year} value={year.toString()}>{year} años</SelectItem>
                                ))}
                            </SelectContent>
                        </Select>
                        <Input
                            type="number"
                            value={interestRate}
                            onChange={(e) => setInterestRate(Number(e.target.value))}
                            placeholder="Tasa de interés anual (%)"
                        />
                    </div>
                    <Button onClick={calculateInvestment} className="w-full">Simular Inversión</Button>
                </div>
                {data.length > 0 && (
                    <div className="h-[300px]">
                        <ResponsiveContainer width="100%" height="100%">
                            <LineChart data={data}>
                                <CartesianGrid strokeDasharray="3 3" />
                                <XAxis dataKey="year" />
                                <YAxis />
                                <Tooltip />
                                <Legend />
                                <Line type="monotone" dataKey="amount" stroke="#8884d8" name="Monto Total" />
                            </LineChart>
                        </ResponsiveContainer>
                    </div>
                )}
            </CardContent>
        </Card>
    )
}

