import { User } from "@/types/user";
import { create } from "zustand";
import { persist } from "zustand/middleware";

interface UserStore {
  customToken: string;
  iUpiUser: User | string,
  setCustomToken: (token?: string) => void;
  setiUpiUser: (user?: User) => void;
}

export const useUserStore = create<UserStore>()(
  persist(
    (set) => ({
      customToken: "",
      iUpiUser: "",
      setCustomToken: (token?: string) => set({ customToken: token ?? "" }),
      setiUpiUser: (user?: User) => set({iUpiUser: user ?? ""})
    }),
    { name: "userStore" }
  )
);
