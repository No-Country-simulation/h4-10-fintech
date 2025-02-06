import { Lato } from "next/font/google";
import type { Metadata } from "next";
import "./globals.css";
import { UserProvider } from "@auth0/nextjs-auth0/client";
import Providers from "@/app/providers";
import NavBar from "@/components/NavBar";
import Footer from "@/components/Footer";

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
      <UserProvider>
          <body className="min-h-screen w-full">
            <Providers>
              <NavBar />
              <main className="min-h-[502px] w-full max-w-desktop mx-auto">
                {children}
              </main>
              <Footer />
            </Providers>
          </body>
      </UserProvider>
    </html>
  );
}
