import { GoalsProgress } from "@/features/dashboard/components/goals-progress";

export default function ObjetivosPage() {
  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold">Objetivos Financieros</h1>
      <GoalsProgress />
    </div>
  );
}
