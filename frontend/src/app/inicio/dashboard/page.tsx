/* "use client"

import { DashboardHeader } from "@/features/dashboard/components/dashboard-header";
import { FinancialSummary } from "@/features/dashboard/components/financial-summary";
import { GoalsProgress } from "@/features/dashboard/components/goals-progress";
import { RecommendedInvestments } from "@/features/dashboard/components/recommended-investments";
import { RecentTransactions } from "@/features/dashboard/components/recent-transactions";
import { PersonalizedNews } from "@/features/dashboard/components/personalized-news";
import { UserRewards } from "@/features/dashboard/components/user-rewards";
import { InvestmentSimulator } from "@/features/dashboard/components/investment-simulator";
import { FinancialEducation } from "@/features/dashboard/components/financial-education";
import { NotificationsCenter } from "@/features/dashboard/components/notifications-center";
import { ExpenseAnalysis } from "@/features/dashboard/components/expense-analysis";
import { FinancialInstrumentComparator } from "@/features/dashboard/components/financial-instrument-comparator";
import { useAuth } from "@/hooks/useAuth";

export default function HomePage() {
  const {iUpiUser, customToken} = useAuth();
  
  return (
    <div className="space-y-6">
      <DashboardHeader />
      <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
        <FinancialSummary />
        <GoalsProgress />
        <RecommendedInvestments />
      </div>
      <RecentTransactions />
      <div className="grid gap-6 md:grid-cols-2">
        <PersonalizedNews />
        <UserRewards />
      </div>
      <div className="grid gap-6 md:grid-cols-2">
        <InvestmentSimulator />
        <FinancialEducation />
      </div>
      <div className="grid gap-6 md:grid-cols-2">
        <NotificationsCenter />
        <ExpenseAnalysis />
      </div>
      <FinancialInstrumentComparator />
    </div>
  );
}
 */