import Link from "next/link";
import Image from "next/image";
import iUpi from "@/assets/iUpi.svg";
import SessionButtons from "@/components/SessionButtons";

export default function NavBar() {

  return (
    <header className="w-full h-[72px] font-sans bg-primary sticky top-0 z-50">
      <div className="max-w-desktop flex justify-between items-center h-[72px] mx-auto px-4 md:px-8 xl:px-0">
        <Link
          href="/"
        >
          <Image src={iUpi} width={64} height={64} alt="iupi logo" />
        </Link>

        <div className="flex justify-between items-center">
          <SessionButtons />
        </div>
      </div>
    </header>
  );
}
