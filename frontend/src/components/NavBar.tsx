import { Button } from "@/components/ui/button";
import Link from "next/link";
import IupiLogo from "@/assets/IupiLogo";

const links = ["Metas", "Inversiones", "Comunidad", "Educación financiera"];

export default function NavBar() {
  return (
    <header className="w-full h-[72px] bg-slate-200 font-sans">
      <div className="max-w-desktop flex justify-between items-center h-[72px] mx-auto bg-white">
        <IupiLogo/>
      {links.map((link) => (
        <Button key={link} variant="link">
          <Link href="#">{link}</Link>
        </Button>
      ))}
      <Button variant="secondary">
      <Link href="#">Ingresar a tu cuenta</Link>
        </Button>
      </div>
    </header>
  );
}
