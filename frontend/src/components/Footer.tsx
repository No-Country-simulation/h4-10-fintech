import iUpi from "@/assets/iUpi.svg"
import XLogo from "@/assets/social/x.svg";
import InstagramLogo from "@/assets/social/instagram.svg";
import YouTubeLogo from "@/assets/social/youtube.svg";
import LinkedinLogo from "@/assets/social/linkedin.svg";
import Image from "next/image";
import Link from "next/link";

const navLinks = [
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

const socialLinks = [
  { link: "https://x.com", image: XLogo, alt: "iUpi en X" },
  {
    link: "https://instagram.com",
    image: InstagramLogo,
    alt: "iUpi en Instagram",
  },
  { link: "https://youtube.com", image: YouTubeLogo, alt: "iUpi en YouTube" },
  {
    link: "https://linkedin.com",
    image: LinkedinLogo,
    alt: "iUpi en Linkedin",
  },
];

export default function Footer() {
  return (
    <footer className="bg-primary text-primary-foreground w-full">
      <div className="max-w-desktop grid grid-cols-1 xs:grid-cols-2 md:grid-cols-4 gap-6 lg:gap-0 lg:justify-items-center pt-5 pb-8 mx-2 lg:mx-auto">
        <section className="grid grid-cols-1 content-start">
          <Image src={iUpi} alt="iupi logo" width={61} height={33} />
          <div className="grid grid-cols-4 gap-2 mt-1">
            {socialLinks.map((media) => (
              <SocialMediaLink
                link={media.link}
                image={media.image}
                alt={media.alt}
                key={media.alt}
              />
            ))}
          </div>
        </section>
        {navLinks.map((section) => (
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

interface SocialMediaLinkProps {
  link: string,
  image: string,
  alt: string,
}

function SocialMediaLink({ link, image, alt }: SocialMediaLinkProps) {
  return (
    <Link href={link}>
      <Image src={image} alt={alt} width={24} height={24} />
    </Link>
  );
}
