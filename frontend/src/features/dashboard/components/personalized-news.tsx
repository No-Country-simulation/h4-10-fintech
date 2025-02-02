"use client"

import { useState, useEffect } from 'react'
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { Newspaper, ExternalLink } from 'lucide-react'

interface NewsItem {
    id: number;
    title: string;
    source: string;
    category: string;
    url: string;
}

// Simulación de una función que obtiene noticias de una API
const fetchNews = async (): Promise<NewsItem[]> => {
    // En una implementación real, esto sería una llamada a una API externa
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve([
                { id: 1, title: "El Banco Central anuncia nuevas medidas económicas", source: "Economía Hoy", category: "Economía", url: "#" },
                { id: 2, title: "Acciones tecnológicas alcanzan nuevos máximos", source: "Mercado Financiero", category: "Inversiones", url: "#" },
                { id: 3, title: "Cómo diversificar tu portafolio en tiempos de incertidumbre", source: "Finanzas Personales", category: "Consejos", url: "#" },
                { id: 4, title: "Nuevas oportunidades de inversión en energías renovables", source: "Green Invest", category: "Tendencias", url: "#" },
                { id: 5, title: "Análisis: Impacto de la inflación en los mercados globales", source: "Global Economics", category: "Análisis", url: "#" },
            ])
        }, 1000) // Simulamos un delay de 1 segundo
    })
}

export function PersonalizedNews() {
    const [newsItems, setNewsItems] = useState<NewsItem[]>([])
    const [isLoading, setIsLoading] = useState(true)

    useEffect(() => {
        const loadNews = async () => {
            setIsLoading(true)
            try {
                const news = await fetchNews()
                setNewsItems(news)
            } catch (error) {
                console.error("Error fetching news:", error)
            } finally {
                setIsLoading(false)
            }
        }
        loadNews()
    }, [])

    return (
        <Card className="bg-white dark:bg-gray-800 shadow-sm">
            <CardHeader>
                <CardTitle className="text-lg font-semibold flex items-center">
                    <Newspaper className="mr-2 h-5 w-5" />
                    Noticias Financieras Personalizadas
                </CardTitle>
            </CardHeader>
            <CardContent>
                {isLoading ? (
                    <p>Cargando noticias...</p>
                ) : (
                    <ul className="space-y-4">
                        {newsItems.map((item) => (
                            <li key={item.id} className="border-b border-gray-200 dark:border-gray-700 pb-4 last:border-b-0 last:pb-0">
                                <a href={item.url} className="block hover:bg-gray-50 dark:hover:bg-gray-700 rounded-lg p-2 transition duration-150 ease-in-out">
                                    <h3 className="text-sm font-medium text-gray-900 dark:text-gray-100 flex items-center justify-between">
                                        {item.title}
                                        <ExternalLink className="h-4 w-4 text-gray-500" />
                                    </h3>
                                    <p className="text-sm text-gray-500 dark:text-gray-400 mt-1">{item.source}</p>
                                    <Badge variant="secondary" className="mt-2">{item.category}</Badge>
                                </a>
                            </li>
                        ))}
                    </ul>
                )}
            </CardContent>
        </Card>
    )
}

