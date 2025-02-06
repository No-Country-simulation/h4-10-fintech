"use client"

import Link from "next/link";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import homeIcon from "@/features/dashboard/sidebar/assets/homeIcon.svg";
import balanceIcon from "@/features/dashboard/sidebar/assets/balanceIcon.svg";
import goalsIcon from "@/features/dashboard/sidebar/assets/goalsIcon.svg";
import investmentsIcon from "@/features/dashboard/sidebar/assets/investmentsIcon.svg";
import communityIcon from "@/features/dashboard/sidebar/assets/communityIcon.svg";
import signOutIcon from "@/features/dashboard/sidebar/assets/signoutIcon.svg";
import Image from "next/image";
import { usePathname } from "next/navigation";
import { cn } from "@/lib/utils";
import { Avatar, AvatarFallback, /* AvatarImage */ } from "@/components/ui/avatar";
import { Tooltip, TooltipContent, TooltipProvider, TooltipTrigger } from "@/components/ui/tooltip";

const dashboardLinks = [
  {
    title: "Inicio",
    href: "/dashboard",
    icon: homeIcon,
  },
  {
    title: "Resumen financiero",
    href: "/dashboard/resumen",
    icon: balanceIcon,
  },
  {
    title: "Objetivos",
    href: "/dashboard/objetivos",
    icon: goalsIcon,
  },
  {
    title: "Inversiones",
    href: "/dashboard/inversiones",
    icon: investmentsIcon,
  },
  {
    title: "Radiografía financiera",
    href: "/dashboard/radiografia",
    icon: investmentsIcon,
  },
  {
    title: "Comunidad",
    href: "/dashboard/community",
    icon: communityIcon,
  },
];

function DashboardSidebar() {
  const pathname = usePathname();

if (false) return;

  return (
    <aside className="bg-card flex flex-col items-center w-16 md:w-64 h-full pt-8 pb-24">
      <TooltipProvider>
        <div className="w-4/5 flex items-center gap-3">
          <Avatar className="inline-block ml-1 md:ml-0">
            {/* <AvatarImage src={user?.picture ?? "iU"} /> */}
            <AvatarFallback>{"user?.email[0]"}</AvatarFallback>
          </Avatar>
          <div className="hidden md:block">
            <p className="font-bold">¡Hola{/* {user?.nombre && user.nombre.search("@") >= 0 ? "!" : `, ${user?.nombre}!`} */}</p>
            <p className="text-sm">{"user?.email"}</p>
          </div>
        </div>

        <Input
          placeholder="Buscar"
          className="w-4/5 mt-12 hidden md:inline-block"
        />

        <div className="w-4/5 pt-12 pb-36">
          {dashboardLinks.map((link) => (
            <Tooltip key={link.title}>
              <TooltipTrigger asChild>
                <Link href={link.href}>
                  <Button
                    variant="ghost"
                    className={cn(
                      "w-full h-14 text-start inline-block p-4 hover:bg-[#eee] hover:text-foreground",
                      pathname === link.href && "bg-[#D0D0D0]"
                    )}
                  >
                    <Image
                      src={link.icon}
                      alt={link.title}
                      className="inline mr-2 -mt-1"
                    />
                    <span className="hidden md:inline-block">{link.title}</span>
                  </Button>
                </Link>
              </TooltipTrigger>
              <TooltipContent className="md:hidden">
                <p>{link.title}</p>
              </TooltipContent>
            </Tooltip>
          ))}
        </div>

        <div className="w-4/5">
          <Link href="/api/auth/logout">
            <Button
              variant="ghost"
              className={cn(
                "w-full h-14 text-start inline-block p-4 hover:bg-[#eee] hover:text-foreground"
              )}
            >
              <Image
                src={signOutIcon}
                alt="Cerrar sesión"
                className="inline mr-2 -mt-1"
              />
              <span>Cerrar sesión</span>
            </Button>
          </Link>
        </div>
      </TooltipProvider>
    </aside>
  );
}

export default DashboardSidebar;
