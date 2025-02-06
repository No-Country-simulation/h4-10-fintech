import { User } from "@/types/data/types";
import { create } from "zustand";
import { persist } from "zustand/middleware";

interface UserStore {
  iUpiUser: User | string;
  setiUpiUser: (user?: User) => void;
}

export const useUserStore = create<UserStore>()(
  persist(
    (set) => ({
      iUpiUser: "",
      setiUpiUser: (user?: User) => set({ iUpiUser: user ?? "" }),
    }),
    { name: "userStore" }
  )
);
