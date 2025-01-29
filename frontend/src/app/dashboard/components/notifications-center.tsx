"use client"

import { useState } from 'react'
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Bell, Info, TrendingUp, AlertTriangle } from 'lucide-react'
import { Badge } from "@/components/ui/badge"

interface Notification {
    id: number
    type: 'info' | 'alert' | 'success'
    message: string
    date: string
}

const initialNotifications: Notification[] = [
    { id: 1, type: 'info', message: 'Nueva oportunidad de inversión disponible', date: '2023-06-15' },
    { id: 2, type: 'alert', message: 'Recuerda realizar tu aporte mensual', date: '2023-06-14' },
    { id: 3, type: 'success', message: 'Has alcanzado tu meta de ahorro mensual', date: '2023-06-13' },
]

export function NotificationsCenter() {
    const [notifications, setNotifications] = useState(initialNotifications)

    const getIcon = (type: string) => {
        switch (type) {
            case 'info':
                return <Info className="h-5 w-5 text-blue-500" />
            case 'alert':
                return <AlertTriangle className="h-5 w-5 text-yellow-500" />
            case 'success':
                return <TrendingUp className="h-5 w-5 text-green-500" />
            default:
                return <Bell className="h-5 w-5" />
        }
    }

    return (
        <Card className="bg-white dark:bg-gray-800 shadow-sm">
            <CardHeader>
                <CardTitle className="text-lg font-semibold flex items-center">
                    <Bell className="mr-2 h-5 w-5" />
                    Centro de Notificaciones
                </CardTitle>
            </CardHeader>
            <CardContent>
                <ul className="space-y-4">
                    {notifications.map((notification) => (
                        <li key={notification.id} className="flex items-start space-x-3 p-3 bg-gray-50 dark:bg-gray-700 rounded-lg">
                            {getIcon(notification.type)}
                            <div className="flex-1">
                                <p className="text-sm">{notification.message}</p>
                                <p className="text-xs text-gray-500 dark:text-gray-400 mt-1">{notification.date}</p>
                            </div>
                            <Badge variant={notification.type === 'info' ? 'default' : notification.type === 'alert' ? 'destructive' : 'secondary'}>
                                {notification.type}
                            </Badge>
                        </li>
                    ))}
                </ul>
            </CardContent>
        </Card>
    )
}

