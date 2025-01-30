"use client"

import { useState } from 'react'
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"

export function AdminPanel() {
    const [users, /* setUsers */] = useState([
        { id: '1', name: 'Juan Pérez', email: 'juan@example.com', role: 'standard' },
        { id: '2', name: 'María García', email: 'maria@example.com', role: 'standard' },
        { id: '3', name: 'Admin User', email: 'admin@example.com', role: 'admin' },
    ])

    return (
        <div className="space-y-6">
            <Card>
                <CardHeader>
                    <CardTitle>Panel de Administración</CardTitle>
                </CardHeader>
                <CardContent>
                    <h3 className="text-lg font-semibold mb-4">Gestión de Usuarios</h3>
                    <Table>
                        <TableHeader>
                            <TableRow>
                                <TableHead>Nombre</TableHead>
                                <TableHead>Email</TableHead>
                                <TableHead>Rol</TableHead>
                                <TableHead>Acciones</TableHead>
                            </TableRow>
                        </TableHeader>
                        <TableBody>
                            {users.map((user) => (
                                <TableRow key={user.id}>
                                    <TableCell>{user.name}</TableCell>
                                    <TableCell>{user.email}</TableCell>
                                    <TableCell>{user.role}</TableCell>
                                    <TableCell>
                                        <Button variant="outline" size="sm">Editar</Button>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </CardContent>
            </Card>
            <Card>
                <CardHeader>
                    <CardTitle>Monitoreo del Sistema</CardTitle>
                </CardHeader>
                <CardContent>
                    <div className="space-y-4">
                        <div>
                            <h4 className="font-medium mb-2">Estado de las APIs</h4>
                            <div className="flex space-x-2">
                                <div className="bg-green-500 text-white px-3 py-1 rounded-full text-sm">API Noticias: OK</div>
                                <div className="bg-green-500 text-white px-3 py-1 rounded-full text-sm">API Inversiones: OK</div>
                            </div>
                        </div>
                        <div>
                            <h4 className="font-medium mb-2">Incidencias Recientes</h4>
                            <p className="text-sm text-gray-600">No hay incidencias recientes.</p>
                        </div>
                    </div>
                </CardContent>
            </Card>
        </div>
    )
}

