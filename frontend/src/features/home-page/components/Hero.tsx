// import Image from "next/image";
// import heroImage from "@/assets/hero.jpg"

export default function Hero() {
  return (
    <section className="text-center">
      {/* <Image
        src={heroImage}
        width="1280"
        height="40"
        alt="Ahorro e inversiones"
      /> */}
      <div className="text-balance mt-[320px]">
        <h1 className="font-bold text-6xl">iUpi</h1>
        <p className="uppercase text-3xl tracking-widest py-4">
          Ahorro & inversiones
        </p>
      </div>
    </section>
  );
}