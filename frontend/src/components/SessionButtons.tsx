/* eslint-disable @next/next/no-html-link-for-pages */
"use client"
import Image from "next/image";
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover";
import { useUser } from "@auth0/nextjs-auth0/client";
import BellIcon from "@/assets/bell-icon.svg"
import { Button } from "@/components/ui/button";

export default function SessionButtons() {
  const {user} = useUser();

  if (user) return (
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
      <a href="/api/auth/login">
        <Button variant="secondary" className="mx-2 text-base">
          Registrarse
        </Button>
      </a>
      <a href="https://h4-10-fintech.onrender.com/">
        <Button
          variant="outline"
          className="text-base hover:bg-[#eee] hover:text-foreground"
        >
          Iniciar sesión
        </Button>
      </a>
    </>
  );
}