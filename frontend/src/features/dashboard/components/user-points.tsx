import { Trophy } from 'lucide-react'

export function UserPoints() {
    // En una implementación real, estos puntos vendrían de un estado global o una base de datos
    const points = 150

    return (
        <div className="flex items-center justify-center p-4 bg-blue-100 dark:bg-blue-900 rounded-lg">
            <Trophy className="h-5 w-5 text-yellow-500 mr-2" />
            <span className="font-medium">{points} puntos</span>
        </div>
    )
}

