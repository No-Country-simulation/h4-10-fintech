import { InvestmentManagement } from "@/app/dashboard/components/investment-management"
import { RecommendedInvestments } from "@/app/dashboard/components/recommended-investments"

export default function InversionesPage() {
    return (
        <div className="space-y-6">
            <h1 className="text-2xl font-bold">Gestión de Inversiones</h1>
            <div className="grid gap-6 md:grid-cols-2">
                <RecommendedInvestments />
                <InvestmentManagement />
            </div>
        </div>
    )
}

