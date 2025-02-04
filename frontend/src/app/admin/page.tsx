"use client";

import { useAuth } from "@/context/auth-context";
import { AdminPanel } from "@/features/dashboard/components/admin-panel";
import { useRouter } from "next/navigation";
import { useEffect } from "react";

export default function AdminPage() {
  const { user } = useAuth();
  const router = useRouter();

  useEffect(() => {
    if (user && user.role !== "admin") {
      router.push("/");
    }
  }, [user, router]);

  if (!user || user.role !== "admin") {
    return null;
  }

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold">Panel de Administración</h1>
      <AdminPanel />
    </div>
  );
}
