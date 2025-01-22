import Link from "next/link";
import Image from "next/image"
import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator"
import { Input } from "@/components/ui/input"

const navLinks = ["Metas", "Inversiones", "Comunidad", "Educación financiera"];

export default function NavBar() {
  return (
    <header className="w-full h-[72px] font-sans bg-primary">
      <div className="max-w-desktop flex justify-between items-center h-[72px] mx-auto">
        <Link href="/">
        <Image src="/Iupi.png" width={64} height={64} alt="iupi logo"/>
        </Link>
        
        <div className="flex justify-between items-center">
      {navLinks.map((link) => (
        <Button key={link} variant="link" className="text-white font-bold text-base">
          <Link href="#">{link}</Link>
        </Button>
      ))}
      <Separator orientation="vertical" className="font-bold"/>
      <Input className="mx-6 text-base" placeholder="Buscar"/>
      <Button className="mx-4 text-base">
      <Link href="/sign-up">Registrarse</Link>
        </Button>
        <Button variant="outline" className="text-base">
      <Link href="/sign-in">Iniciar sesión</Link>
        </Button>
          </div>
      </div>
    </header>
  );
}
