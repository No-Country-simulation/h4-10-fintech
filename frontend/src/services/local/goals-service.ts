import { allUsersGoals } from "@/data/goals";
import { wait } from "@/lib/wait";
import { Goal } from "@/types/local/types";

export async function getUserGoals(userId?: number)  {
  await wait(2000);
  let goals: Goal[] = []
  if (userId) {
    goals = allUsersGoals[userId - 1];
  }
  return goals;
}