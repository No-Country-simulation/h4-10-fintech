"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import {
  Home,
  PieChart,
  Target,
  DollarSign,
  FileText,
  Users,
  Settings,
  LogOut,
} from "lucide-react";
import { cn } from "@/lib/utils";
import { UserPoints } from "@/features/dashboard/components/user-points";
import { useAuth } from "@/context/auth-context";
import { Button } from "@/components/ui/button";

const menuItems = [
  { icon: Home, label: "Inicio", href: "/dashboard" },
  { icon: PieChart, label: "Resumen Financiero", href: "/dashboard/resumen" },
  { icon: Target, label: "Objetivos", href: "/dashboard/objetivo" },
  { icon: DollarSign, label: "Inversiones", href: "/dashboard/inversiones" },
  {
    icon: FileText,
    label: "Radiografía Financiera",
    href: "/dashboard/radiografia",
  },
  { icon: Users, label: "Comunidad", href: "/dashboard/comunidad" },
];

export function AppSidebar() {
  const pathname = usePathname();
  const { user, logout } = useAuth();

  return (
    <aside className="w-64 bg-white dark:bg-gray-800 border-r border-gray-200 dark:border-gray-700">
      <div className="h-full flex flex-col">
        <div className="px-6 py-4 border-b border-gray-200 dark:border-gray-700">
          <h2 className="text-2xl font-bold bg-clip-text text-transparent bg-gradient-to-r from-blue-500 to-teal-400">
            Iupi
          </h2>
        </div>
        <nav className="flex-1 overflow-y-auto py-4">
          <ul className="space-y-1">
            {menuItems.map((item) => (
              <li key={item.href}>
                <Link
                  href={item.href}
                  className={cn(
                    "flex items-center gap-3 px-6 py-3 text-sm font-medium transition-colors",
                    pathname === item.href
                      ? "bg-blue-100 text-blue-600 dark:bg-blue-900 dark:text-blue-300"
                      : "text-gray-700 hover:bg-gray-100 dark:text-gray-300 dark:hover:bg-gray-800"
                  )}
                >
                  <item.icon className="h-5 w-5" />
                  {item.label}
                </Link>
              </li>
            ))}
            {user && user.role === "admin" && (
              <li>
                <Link
                  href="/admin"
                  className={cn(
                    "flex items-center gap-3 px-6 py-3 text-sm font-medium transition-colors",
                    pathname === "/admin"
                      ? "bg-blue-100 text-blue-600 dark:bg-blue-900 dark:text-blue-300"
                      : "text-gray-700 hover:bg-gray-100 dark:text-gray-300 dark:hover:bg-gray-800"
                  )}
                >
                  <Settings className="h-5 w-5" />
                  Panel de Admin
                </Link>
              </li>
            )}
          </ul>
        </nav>
        <div className="p-4">
          <UserPoints />
        </div>
        <div className="border-t border-gray-200 dark:border-gray-700 p-4">
          <Button
            variant="outline"
            className="w-full flex items-center justify-center"
            onClick={logout}
          >
            <LogOut className="h-4 w-4 mr-2" />
            Cerrar Sesión
          </Button>
        </div>
        <div className="border-t border-gray-200 dark:border-gray-700 p-4">
        </div>
      </div>
    </aside>
  );
}
