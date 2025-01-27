import Image from "next/image";
import heroImage from "@/assets/hero.webp"

export default function Hero() {
  return (
    <section className="text-center min-h-dvh grid place-content-center w-full">
      <Image
        className="absolute inset-x-0 w-screen min-h-80 opacity-60 xs:h-dvh object-cover"
        src={heroImage}
        width="1280"
        height="40"
        alt="Ahorro e inversiones"
      />
      <div className="-mt-[320px] xs:-mt-4 w-full mx-auto z-10 text-balance">
        <h1 className="font-bold text-6xl md:text-8xl">iUpi</h1>
        <p className="uppercase text-2xl md:text-3xl tracking-widest py-4">
          Ahorro & inversiones
        </p>
      </div>
    </section>
  );
}