"use client"

import { useState } from 'react'
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Progress } from "@/components/ui/progress"
import { Badge } from "@/components/ui/badge"
import { Book, CheckCircle } from 'lucide-react'

const lessons = [
    { id: 1, title: "Introducción a las Finanzas Personales", duration: "10 min" },
    { id: 2, title: "Presupuesto y Ahorro", duration: "15 min" },
    { id: 3, title: "Inversiones Básicas", duration: "20 min" },
    { id: 4, title: "Gestión de Deudas", duration: "15 min" },
    { id: 5, title: "Planificación para el Retiro", duration: "20 min" },
]

export function FinancialEducation() {
    const [completedLessons, setCompletedLessons] = useState<number[]>([])

    const markAsCompleted = (lessonId: number) => {
        if (!completedLessons.includes(lessonId)) {
            setCompletedLessons([...completedLessons, lessonId])
        }
    }

    const progress = (completedLessons.length / lessons.length) * 100

    return (
        <Card className="bg-white dark:bg-gray-800 shadow-sm">
            <CardHeader>
                <CardTitle className="text-lg font-semibold flex items-center">
                    <Book className="mr-2 h-5 w-5" />
                    Educación Financiera
                </CardTitle>
            </CardHeader>
            <CardContent>
                <div className="mb-4">
                    <Progress value={progress} className="h-2" />
                    <p className="text-sm text-gray-600 dark:text-gray-400 mt-2">
                        {completedLessons.length} de {lessons.length} lecciones completadas
                    </p>
                </div>
                <ul className="space-y-4">
                    {lessons.map((lesson) => (
                        <li key={lesson.id} className="flex items-center justify-between">
                            <div>
                                <h3 className="font-medium">{lesson.title}</h3>
                                <p className="text-sm text-gray-600 dark:text-gray-400">{lesson.duration}</p>
                            </div>
                            {completedLessons.includes(lesson.id) ? (
                                <Badge variant="secondary" className="flex items-center">
                                    <CheckCircle className="mr-1 h-4 w-4" />
                                    Completado
                                </Badge>
                            ) : (
                                <Button size="sm" onClick={() => markAsCompleted(lesson.id)}>Comenzar</Button>
                            )}
                        </li>
                    ))}
                </ul>
            </CardContent>
        </Card>
    )
}

