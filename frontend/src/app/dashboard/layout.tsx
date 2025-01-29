import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import { AppSidebar } from '@/app/dashboard/components/sidebar'
import { AuthProvider } from '@/app/context/auth-context'

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'Iupi Dashboard',
  description: 'Tu dashboard financiero personal',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="es">
      <body className={inter.className}>
        <AuthProvider>
          <div className="flex h-screen bg-gray-100 dark:bg-gray-900">
            <AppSidebar />
            <main className="flex-1 overflow-y-auto p-8">
              <div className="max-w-7xl mx-auto">
                {children}
              </div>
            </main>
          </div>
        </AuthProvider>
      </body>
    </html>
  )
}

