import type { Metadata } from "next";
import { Lato } from "next/font/google";
import "./globals.css";
import NavBar from "@/components/NavBar";
import Footer from "@/components/Footer";
import Providers from "@/app/providers";
import { AuthProvider } from "@/app/context/auth-context";

const lato = Lato({
  variable: "--font-lato",
  subsets: ["latin"],
  weight: ["400", "700"],
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
      <body className="min-h-screen w-full">
        <AuthProvider>
        <Providers>
          <NavBar />
          <main className="min-h-[502px] w-full max-w-desktop mx-auto">
            {children}
          </main>
          <Footer />
        </Providers>
        </AuthProvider>
      </body>
    </html>
  );
}
