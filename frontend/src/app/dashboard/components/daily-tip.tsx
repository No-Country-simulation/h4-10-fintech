import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Lightbulb } from 'lucide-react'

const tips = [
    {
        title: "¿Qué es un CEDEAR?",
        content: "Un CEDEAR (Certificado de Depósito Argentino) es un instrumento que permite invertir en acciones de empresas extranjeras desde Argentina."
    },
    {
        title: "Diversificación de Cartera",
        content: "La diversificación es una estrategia que consiste en distribuir tus inversiones en diferentes activos para reducir el riesgo."
    },
    {
        title: "Interés Compuesto",
        content: "El interés compuesto es el interés que se gana sobre el interés previamente acumulado, lo que puede acelerar significativamente el crecimiento de tus ahorros."
    }
]

export function DailyTip() {
    // Seleccionar un tip aleatorio
    const randomTip = tips[Math.floor(Math.random() * tips.length)]

    return (
        <Card className="bg-white dark:bg-gray-800 shadow-sm">
            <CardHeader>
                <CardTitle className="text-lg font-semibold flex items-center">
                    <Lightbulb className="mr-2 h-5 w-5" />
                    Tip Financiero del Día
                </CardTitle>
            </CardHeader>
            <CardContent>
                <h3 className="font-medium text-lg mb-2">{randomTip.title}</h3>
                <p className="text-gray-600 dark:text-gray-300">{randomTip.content}</p>
            </CardContent>
        </Card>
    )
}

