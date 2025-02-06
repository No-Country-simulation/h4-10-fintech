"use client"
import Image from "next/image";
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover";
import BellIcon from "@/assets/bell-icon.svg"
import { Button } from "@/components/ui/button";
import Link from "next/link";
import { useUserStore } from "@/store/session-store";

export default function SessionButtons() {
  const iUpiUser = useUserStore(state => state.iUpiUser);

  if (iUpiUser) return (
    <Popover>
      <PopoverTrigger>
        <Image src={BellIcon} alt="Notificaciones"/>
      </PopoverTrigger>
      <PopoverContent>
        No hay notificaciones nuevas
      </PopoverContent>
    </Popover>
  );
  return (
    <>
      <Link href="/inicio/sign-up">
        <Button variant="secondary" className="mx-2 text-base">
          Registrarse
        </Button>
      </Link>
      <Link href="/inicio/sign-in">
        <Button
          variant="outline"
          className="text-base hover:bg-[#eee] hover:text-foreground"
        >
          Iniciar sesión
        </Button>
      </Link>
    </>
  );
}