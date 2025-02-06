import { User } from "@/types/local/types";
import { create } from "zustand";
import { persist } from "zustand/middleware";

interface UserStore {
  iUpiUser: User | string;
  setIUpiUser: (user?: User) => void;
}

export const useUserStore = create<UserStore>()(
  persist(
    (set) => ({
      iUpiUser: "",
      setIUpiUser: (user?: User) => set({ iUpiUser: user ?? "" }),
    }),
    { name: "userStore" }
  )
);
