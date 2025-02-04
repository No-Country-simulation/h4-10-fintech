import { Goal } from "../types/goalTypes";

const baseUrl = `${process.env.NEXT_PUBLIC_BASE_API_URL}/objetivos-financieros`;

export async function getUserGoals() {
  const res = await fetch(`${baseUrl}`);
  const data = await res.json();
  return data.data;
}

export async function postGoal(newGoal: Omit<Goal, id | montoActual>) {
  const options: RequestInit = {
    headers: {
      method: "POST",
    },
    body: JSON.stringify(newGoal),
  };
  const res = await fetch(baseUrl, options);
  const data = await res.json();
  return data.data;
}
