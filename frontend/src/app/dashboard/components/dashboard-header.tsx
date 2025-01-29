import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"

export function DashboardHeader() {
    return (
        <Card className="bg-white dark:bg-gray-800 shadow-sm">
            <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
                <div>
                    <CardTitle className="text-2xl font-bold bg-clip-text text-transparent bg-gradient-to-r from-blue-500 to-teal-400">
                        Bienvenido a Iupi
                    </CardTitle>
                    <CardDescription>
                        Tu asistente financiero personal
                    </CardDescription>
                </div>
                <Avatar className="h-12 w-12">
                    <AvatarImage src="/avatar.png" alt="Avatar" />
                    <AvatarFallback>US</AvatarFallback>
                </Avatar>
            </CardHeader>
            <CardContent>
                <p className="text-sm text-muted-foreground">
                    Última actualización: {new Date().toLocaleDateString()}
                </p>
            </CardContent>
        </Card>
    )
}

