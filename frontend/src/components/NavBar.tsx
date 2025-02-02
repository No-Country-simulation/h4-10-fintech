import Link from "next/link";
import Image from "next/image"
import iUpi from "@/assets/iUpi.svg"
import SessionButtons from "@/components/SessionButtons";

// const navLinks = ["Metas", "Inversiones", "Comunidad", "Educación financiera"];

export default function NavBar() {
  return (
    <header className="w-full h-[72px] font-sans bg-primary sticky top-0 z-50">
      <div className="max-w-desktop flex justify-between items-center h-[72px] mx-auto">
        <Link href="/">
          <Image src={iUpi} width={64} height={64} alt="iupi logo" />
        </Link>

        <div className="flex justify-between items-center">
          {/* {navLinks.map((link) => (
            <Button
              key={link}
              variant="link"
              className="text-white font-bold text-base"
            >
              <Link href="#">{link}</Link>
            </Button>
          ))}
          <Input className="mx-6 text-base" placeholder="Buscar" /> */}
          <SessionButtons/>
        </div>
      </div>
    </header>
  );
}
