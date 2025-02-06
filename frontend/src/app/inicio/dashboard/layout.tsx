import DashboardSidebar from "@/app/inicio/dashboard/sidebar/components/DashboardSidebar";
import type { Metadata } from "next";

export const metadata: Metadata = {
  title: "Iupi Dashboard",
  description: "Mi dashboard financiero personal",
};

export default function DashboardLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="flex flex-col xs:flex-row h-screen bg-background dark:bg-gray-900">
      <DashboardSidebar />
      <main className="flex-1 overflow-y-auto p-2 xs:p-6 lg:p-8 mx-auto">
        {/* <div className=""></div> */}
        {children}
      </main>
    </div>
  );
}
