import type { Metadata } from "next";
import { Lato } from "next/font/google";
import "./globals.css";
import NavBar from "@/components/NavBar";
import Footer from "@/components/Footer";

const lato = Lato({
  variable: "--font-lato",
  subsets: ["latin"],
  weight: ["400", "700"]
});

export const metadata: Metadata = {
  title: "iUpi - ahorro e inversiones",
  description: "Ahorro e inversiones",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en" className={`${lato.variable} antialiased mx-auto`}>
      <body className="min-h-screen">
        <NavBar/>
        <main className="min-h-[502px] w-full max-w-desktop">
        {children}
        </main>
        <Footer/>
      </body>
    </html>
  );
}
