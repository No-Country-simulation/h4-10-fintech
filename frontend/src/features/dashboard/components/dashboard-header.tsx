import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"

export function DashboardHeader() {
    return (
        <Card className="bg-card dark:bg-gray-800 shadow-sm">
            <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
                <div>
                    <CardTitle className="text-5xl font-bold bg-clip-text text-primary pb-2">
                        Bienvenido a Iupi
                    </CardTitle>
                    <CardDescription className="text-primary text-xl">
                        Tu asistente financiero personal
                    </CardDescription>
                </div>
                {/* <Avatar className="h-12 w-12">
                    <AvatarImage src="/avatar.png" alt="Avatar" />
                    <AvatarFallback>US</AvatarFallback>
                </Avatar> */}
            </CardHeader>
            <CardContent>
                <p className="text-sm pt-3">
                    Última actualización: {new Date().toLocaleDateString()}
                </p>
            </CardContent>
        </Card>
    )
}

