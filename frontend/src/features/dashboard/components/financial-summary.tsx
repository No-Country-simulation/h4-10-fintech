'use client'
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { ArrowUpIcon, ArrowDownIcon, PiggyBankIcon } from 'lucide-react'
import { PieChart, Pie, Cell, ResponsiveContainer, Legend, Tooltip } from 'recharts'

const data = [
    { name: 'Gastos', value: 3500, color: '#ef4444' },
    { name: 'Ahorros', value: 1500, color: '#3b82f6' },
]

export function FinancialSummary() {
    const totalIncome = 5000

    return (
        <Card className="bg-card dark:bg-gray-800 shadow-sm">
            <CardHeader>
                <CardTitle className="text-lg font-semibold">Resumen Financiero</CardTitle>
            </CardHeader>
            <CardContent>
                <div className="space-y-4">
                    <div className="flex items-center justify-between">
                        <div className="flex items-center space-x-2">
                            <ArrowUpIcon className="text-green-500" />
                            <span>Ingresos:</span>
                        </div>
                        <span className="font-bold text-green-600">${totalIncome}</span>
                    </div>
                    <div className="flex items-center justify-between">
                        <div className="flex items-center space-x-2">
                            <ArrowDownIcon className="text-red-500" />
                            <span>Gastos:</span>
                        </div>
                        <span className="font-bold text-red-600">${data[0].value}</span>
                    </div>
                    <div className="flex items-center justify-between">
                        <div className="flex items-center space-x-2">
                            <PiggyBankIcon className="text-blue-500" />
                            <span>Ahorros:</span>
                        </div>
                        <span className="font-bold text-blue-600">${data[1].value}</span>
                    </div>
                </div>
                <div className="mt-6 h-[300px]">
                    <ResponsiveContainer width="100%" height="100%">
                        <PieChart>
                            <Pie
                                data={data}
                                cx="50%"
                                cy="50%"
                                innerRadius={60}
                                outerRadius={80}
                                paddingAngle={5}
                                dataKey="value"
                            >
                                {data.map((entry, index) => (
                                    <Cell key={`cell-${index}`} fill={entry.color} />
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

