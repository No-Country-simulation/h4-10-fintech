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
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";

const dashboardLinks = [
  {
    title: "Inicio",
    href: "/dashboard",
    icon: homeIcon,
  },
  {
    title: "Resumen financiero",
    href: "/dashboard/balance",
    icon: balanceIcon,
  },
  {
    title: "Objetivos",
    href: "/dashboard/goals",
    icon: goalsIcon,
  },
  {
    title: "Inversiones",
    href: "/dashboard/investments",
    icon: investmentsIcon,
  },
  {
    title: "Comunidad",
    href: "/dashboard/community",
    icon: communityIcon,
  }
];

function DashboardSidebar() {
  const pathname = usePathname();
  return (
    <aside className="bg-card flex flex-col items-center max-w-[266px] h-full pt-8 pb-24">
      <div className="w-4/5 flex items-center gap-3">
        <Avatar className="inline-block">
          <AvatarImage src="https://github.com/shadcn.png" />
          <AvatarFallback>CN</AvatarFallback>
        </Avatar>
        <span className="text-lg">¡Hola, John!</span>
      </div>

      <Input placeholder="Buscar" className="w-4/5 mt-12"></Input>

      <div className="w-4/5 pt-12 pb-36">
        {dashboardLinks.map((link) => (
          <Button
            key={link.title}
            variant="ghost"
            className={cn(
              "h-14 w-full",
              pathname === link.href && "bg-secondary text-secondary-foreground"
            )}
          >
            <Link
              href={link.href}
              className="w-full text-start h-full flex items-center"
            >
              <Image
                src={link.icon}
                alt={link.title}
                className={`inline mr-2 ${
                  pathname === link.href && "text-secondary-foreground"
                }`}
              />
              <span>
                {link.title}
              </span>
            </Link>
          </Button>
        ))}
      </div>

      <div className="w-4/5">
        <Button variant="ghost" className="h-14 w-full">
          <Link href="#" className="w-full text-start">
            <Image
              src={signOutIcon}
              alt="Cerrar sesión"
              className="inline mr-2 -mt-1"
            />
            <span>Cerrar sesión</span>
          </Link>
        </Button>
      </div>
    </aside>
  );
}

export default DashboardSidebar;
