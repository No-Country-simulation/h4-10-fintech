"use client"

import { DashboardHeader } from "@/app/inicio/dashboard/components/dashboard-header";
import { FinancialSummary } from "@/app/inicio/dashboard/components/financial-summary";
import { GoalsProgress } from "@/app/inicio/dashboard/components/goals-progress";
import { RecommendedInvestments } from "@/app/inicio/dashboard/components/recommended-investments";
// import { RecentTransactions } from "@/app/inicio/dashboard/components/recent-transactions";
import { PersonalizedNews } from "@/app/inicio/dashboard/components/personalized-news";
// import { UserRewards } from "@/app/inicio/dashboard/components/user-rewards";
import { InvestmentSimulator } from "@/app/inicio/dashboard/components/investment-simulator";
import { FinancialEducation } from "@/app/inicio/dashboard/components/financial-education";
// import { NotificationsCenter } from "@/app/inicio/dashboard/components/notifications-center";
// import { ExpenseAnalysis } from "@/app/inicio/dashboard/components/expense-analysis";
// import { FinancialInstrumentComparator } from "@/app/inicio/dashboard/components/financial-instrument-comparator";
import { useUserStore } from "@/store/session-store";
import { redirect } from "next/navigation";
import { User } from "@/types/local/types";

export default function HomePage() {
  const iUpiUser = useUserStore((state) => state.iUpiUser);
  if (!iUpiUser) {
    return redirect("/");
  }
  const loggedUser = iUpiUser as User;
  return (
    <div className="space-y-6">
      <DashboardHeader />
      <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
        <FinancialSummary />
        <GoalsProgress user={loggedUser}/>
        <RecommendedInvestments />
      </div>
      {/* <RecentTransactions /> */}
      <div className="grid gap-6 md:grid-cols-2">
        <PersonalizedNews />
        <FinancialEducation />
        {/* <UserRewards /> */}
      </div>
      <div className="grid gap-6 md:grid-cols-2">
        <InvestmentSimulator />
      </div>
      {/* <div className="grid gap-6 md:grid-cols-2">
        <NotificationsCenter />
        <ExpenseAnalysis />
      </div>
      <FinancialInstrumentComparator /> */}
    </div>
  );
}
