import Image from "next/image";
import heroImage from "@/assets/hero.webp"

export default function Hero() {
  return (
    <section className="text-center min-h-dvh grid place-content-center">
      <Image
        src={heroImage}
        width="1280"
        height="40"
        alt="Ahorro e inversiones"
        className="absolute inset-x-0 w-screen h-[500px] opacity-60 xs:h-dvh object-cover"
        // className="absolute top-18 w-[1280px] object-cover h-dvh -z-[10]"
      />
      <div className="text-balance -mt-[224px] xs:mt-0 mx-auto w-full z-10">
        <h1 className="font-bold text-6xl md:text-8xl">iUpi</h1>
        <p className="uppercase text-2xl md:text-3xl tracking-widest py-4">
          Ahorro & inversiones
        </p>
      </div>
    </section>
  );
}