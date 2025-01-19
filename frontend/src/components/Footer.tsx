import Image from "next/image";
import Link from "next/link";

const links = [
  {
    title: "Navegación",
    links: ["Inicio", "Quiénes somos", "Nuestros servicios"],
  },
  {
    title: "Ayuda",
    links: ["Preguntas frecuentes", "Contacto", "Términos financieros"],
  },
  {
    title: "Información legal",
    links: ["Términos y condiciones", "Política de privacidad", "Regulaciones"],
  },
];

export default function Footer() {
  return (
    <footer className="bg-slate-200 w-full">
      <div className="grid grid-cols-3 justify-items-center max-w-desktop pt-5 pb-8">
        {/* <section className="flex">
          <Image src="/iupi.png" alt="iupi logo" width={80} height={80} />
          <div>
            <Image
              src="/assets/social/x.svg"
              alt="x logo"
              width={24}
              height={24}
            />
            <Image
              src="/assets/social/x.svg"
              alt="x logo"
              width={24}
              height={24}
            />
            <Image
              src="/assets/social/x.svg"
              alt="x logo"
              width={24}
              height={24}
            />
            <Image
              src="/assets/social/x.svg"
              alt="x logo"
              width={24}
              height={24}
            />
          </div>
        </section> */}
        {links.map((section) => (
          <section key={section.title} className="">
            <h3 className="font-bold text-2xl pb-1">{section.title}</h3>
            {section.links.map((link) => (
              <p key={link} className="py-2">
                <Link href="#">{link}</Link>
              </p>
            ))}
          </section>
        ))}
      </div>
    </footer>
  );
}
