import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { ArrowDownRight, Coffee, ShoppingCart, Zap } from 'lucide-react'

const transactions = [
    { id: 1, name: "Café", amount: -4.50, icon: Coffee, type: "expense" },
    { id: 2, name: "Supermercado", amount: -65.30, icon: ShoppingCart, type: "expense" },
    { id: 3, name: "Salario", amount: 2500, icon: ArrowDownRight, type: "income" },
    { id: 4, name: "Electricidad", amount: -80, icon: Zap, type: "expense" },
]

export function RecentTransactions() {
    return (
        <Card className="bg-white dark:bg-gray-800 shadow-sm">
            <CardHeader>
                <CardTitle className="text-lg font-semibold">Transacciones Recientes</CardTitle>
            </CardHeader>
            <CardContent>
                <div className="space-y-4">
                    {transactions.map((transaction) => (
                        <div key={transaction.id} className="flex items-center justify-between">
                            <div className="flex items-center space-x-4">
                                <div className={`p-2 rounded-full ${transaction.type === 'income' ? 'bg-green-100 text-green-600' : 'bg-red-100 text-red-600'
                                    }`}>
                                    <transaction.icon className="h-4 w-4" />
                                </div>
                                <div>
                                    <p className="text-sm font-medium">{transaction.name}</p>
                                    <p className="text-xs text-gray-500 dark:text-gray-400">
                                        {new Date().toLocaleDateString()}
                                    </p>
                                </div>
                            </div>
                            <span className={`font-semibold ${transaction.type === 'income' ? 'text-green-600' : 'text-red-600'
                                }`}>
                                {transaction.type === 'income' ? '+' : '-'}${Math.abs(transaction.amount).toFixed(2)}
                            </span>
                        </div>
                    ))}
                </div>
            </CardContent>
        </Card>
    )
}

