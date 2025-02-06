'use client';

import Image from "next/image";
import HeroImage from "@/assets/HeroImage.png";
import Steps from "./Steps";
import Choose from "./Choose";
import iupi from "@/assets/iupi.png";

export default function Hero() {
  return (
    <main className="w-full">
      {/* Sección Hero */}
      <section className="relative text-center min-h-dvh grid place-content-center w-full bg-gray-100">
        <Image
          className="absolute inset-0 w-full h-full object-cover"
          src={HeroImage}
          width="1284"
          height="720"
          alt="Ahorro e inversiones"
        />
        
      </section>

      {/* Sección Steps */}
      <section className="w-full  bg-white">
        <div className="max-w-5xl mx-auto">
          <Steps />
        </div>
      </section>

      {/* Puedes agregar más secciones aquí */}
      <section className="w-full py-10 bg-[#1B9CE2]">
        <div className="my-10 mx-auto px-4">
          <Choose />
        </div>
      </section>

      <section className="relative text-center min-h-dvh grid place-content-center w-full bg-gray-100">
        <Image
          className="absolute inset-0 w-full h-full object-cover"
          src={iupi}
          width="1280"
          height="720"
          alt="Ahorro e inversiones"
        />
      </section>
    </main>
  );
}
