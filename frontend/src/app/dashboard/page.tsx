import { DashboardHeader } from "@/app/dashboard/components/dashboard-header"
import { FinancialSummary } from "@/app/dashboard/components/financial-summary"
import { GoalsProgress } from "@/app/dashboard/components/goals-progress"
import { RecommendedInvestments } from "@/app/dashboard/components/recommended-investments"
import { RecentTransactions } from "@/app/dashboard/components/recent-transactions"
import { PersonalizedNews } from "@/app/dashboard/components/personalized-news"
import { UserRewards } from "@/app/dashboard/components/user-rewards"
import { InvestmentSimulator } from "@/app/dashboard/components/investment-simulator"
import { FinancialEducation } from "@/app/dashboard/components/financial-education"
import { NotificationsCenter } from "@/app/dashboard/components/notifications-center"
import { ExpenseAnalysis } from "@/app/dashboard/components/expense-analysis"
import { FinancialInstrumentComparator } from "@/app/dashboard/components/financial-instrument-comparator"

export default function HomePage() {
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
  )
}

