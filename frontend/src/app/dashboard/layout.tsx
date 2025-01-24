import DashboardSidebar from "@/features/dashboard/sidebar/components/DashboardSidebar"
import React from "react"

interface DashboardLayoutProps {
  children: React.ReactNode,
}

export default function DashboardLayout({children}: DashboardLayoutProps) {
  return(
    <section className="grid grid-cols-2 h-full">
      <DashboardSidebar/>
      <section>
        {children}
      </section>
    </section>
  )
}