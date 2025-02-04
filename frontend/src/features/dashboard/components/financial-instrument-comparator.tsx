"use client"

import { useState } from 'react'
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Badge } from "@/components/ui/badge"

interface Instrument {
    id: string
    name: string
    type: string
    risk: 'Bajo' | 'Medio' | 'Alto'
    return: string
    liquidity: 'Baja' | 'Media' | 'Alta'
}

const instruments: Instrument[] = [
    { id: '1', name: 'Plazo Fijo', type: 'Renta Fija', risk: 'Bajo', return: '4-6%', liquidity: 'Baja' },
    { id: '2', name: 'Fondo Común de Inversión', type: 'Mixto', risk: 'Medio', return: '8-12%', liquidity: 'Media' },
    { id: '3', name: 'CEDEAR', type: 'Renta Variable', risk: 'Alto', return: '10-15%', liquidity: 'Alta' },
    { id: '4', name: 'Bono Soberano', type: 'Renta Fija', risk: 'Medio', return: '6-8%', liquidity: 'Media' },
    { id: '5', name: 'ETF S&P 500', type: 'Renta Variable', risk: 'Medio', return: '8-10%', liquidity: 'Alta' },
]

export function FinancialInstrumentComparator() {
    const [selectedInstruments, setSelectedInstruments] = useState<string[]>([])

    const handleInstrumentChange = (value: string) => {
        if (selectedInstruments.includes(value)) {
            setSelectedInstruments(selectedInstruments.filter(i => i !== value))
        } else {
            setSelectedInstruments([...selectedInstruments, value])
        }
    }

    const selectedInstrumentsData = instruments.filter(i => selectedInstruments.includes(i.id))

    return (
        <Card className="bg-white dark:bg-gray-800 shadow-sm">
            <CardHeader>
                <CardTitle className="text-lg font-semibold">Comparador de Instrumentos Financieros</CardTitle>
            </CardHeader>
            <CardContent>
                <div className="mb-4">
                    <Select onValueChange={handleInstrumentChange}>
                        <SelectTrigger>
                            <SelectValue placeholder="Seleccionar instrumentos para comparar" />
                        </SelectTrigger>
                        <SelectContent>
                            {instruments.map((instrument) => (
                                <SelectItem key={instrument.id} value={instrument.id}>
                                    {instrument.name}
                                </SelectItem>
                            ))}
                        </SelectContent>
                    </Select>
                </div>
                {selectedInstrumentsData.length > 0 && (
                    <Table>
                        <TableHeader>
                            <TableRow>
                                <TableHead>Nombre</TableHead>
                                <TableHead>Tipo</TableHead>
                                <TableHead>Riesgo</TableHead>
                                <TableHead>Retorno</TableHead>
                                <TableHead>Liquidez</TableHead>
                            </TableRow>
                        </TableHeader>
                        <TableBody>
                            {selectedInstrumentsData.map((instrument) => (
                                <TableRow key={instrument.id}>
                                    <TableCell>{instrument.name}</TableCell>
                                    <TableCell>{instrument.type}</TableCell>
                                    <TableCell>
                                        <Badge variant={instrument.risk === 'Bajo' ? 'secondary' : instrument.risk === 'Medio' ? 'default' : 'destructive'}>
                                            {instrument.risk}
                                        </Badge>
                                    </TableCell>
                                    <TableCell>{instrument.return}</TableCell>
                                    <TableCell>{instrument.liquidity}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                )}
            </CardContent>
        </Card>
    )
}

