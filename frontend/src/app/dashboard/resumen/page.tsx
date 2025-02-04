import { FinancialSummary } from "@/features/dashboard/components/financial-summary";
import { RecentTransactions } from "@/features/dashboard/components/recent-transactions";

export default function ResumenPage() {
  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold">Resumen Financiero</h1>
      <div className="grid gap-6 md:grid-cols-2">
        <FinancialSummary />
        <RecentTransactions />
      </div>
    </div>
  );
}
