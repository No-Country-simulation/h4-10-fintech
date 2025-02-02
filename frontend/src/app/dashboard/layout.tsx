import type { Metadata } from "next";
import { AppSidebar } from "@/features/dashboard/components/sidebar";

export const metadata: Metadata = {
  title: "Iupi Dashboard",
  description: "Mi dashboard financiero personal",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="flex h-screen bg-gray-100 dark:bg-gray-900">
      <AppSidebar />
      <main className="flex-1 overflow-y-auto p-8">
        <div className="w-full mx-auto">{children}</div>
      </main>
    </div>
  );
}
