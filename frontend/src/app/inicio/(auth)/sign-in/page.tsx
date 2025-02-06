"use client";
import { useState } from "react";
import Image from "next/image";
import { AiFillEye, AiFillEyeInvisible } from "react-icons/ai";
import { users } from "@/data/users";
import { redirect } from "next/navigation";
import { useUserStore } from "@/store/session-store";

const SignIn: React.FC = () => {
    const setIUpiUser = useUserStore((state) => state.setIUpiUser);
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [showPassword, setShowPassword] = useState<boolean>(false);
  const [errors, setErrors] = useState<{ email?: string; password?: string }>(
    {}
  );

  const validateForm = (): boolean => {
    const newErrors: { email?: string; password?: string } = {};

    if (!email) {
      newErrors.email = "El correo electrónico es obligatorio";
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      newErrors.email = "Ingrese un correo electrónico válido";
    }

    if (!password) {
      newErrors.password = "La contraseña es obligatoria";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (validateForm()) {
      const index = users.findIndex((user) => user.email === email);
      setIUpiUser(users[index])
      redirect("/inicio/dashboard")
    }
  };

  return (
    <div className="flex flex-col lg:flex-row items-center justify-center min-h-screen bg-white">
      <div className="w-full h-full flex flex-col md:flex-row overflow-hidden">
        {/* Left Side - Form */}
        <div className="w-full md:w-1/2 p-10  lg:max-w-xl lg:flex flex-col">
          <div className="mb-10">
            <Image src="/logo.png" alt="iUPi" width={497} height={117} />
          </div>
          <h2 className="text-2xl font-bold text-gray-800">Iniciar sesión</h2>
          <p className="text-gray-600 mt-2">
            Inicia sesión para acceder a tu cuenta iUPi
          </p>
          <form className="mt-6" onSubmit={handleSubmit}>
            <div className="relative w-full mb-5">
              <label
                htmlFor="email"
                className="absolute text-sm left-3 top-[-10px] bg-white px-1"
              >
                Correo electrónico
              </label>
              <input
                type="email"
                id="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className={`w-full border ${
                  errors.email ? "border-red-500" : "border-[#79747E]"
                } rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500`}
              />
              {errors.email && (
                <p className="text-red-500 text-sm mt-1">{errors.email}</p>
              )}
            </div>

            <div className="relative w-full mb-6">
              <label
                htmlFor="password"
                className="absolute text-sm left-3 top-[-10px] bg-white px-1"
              >
                Contraseña
              </label>

              <input
                type={showPassword ? "text" : "password"}
                id="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className={`w-full border ${
                  errors.password ? "border-red-500" : "border-[#79747E]"
                } rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500`}
              />
              <span
                onClick={() => setShowPassword(!showPassword)}
                className="absolute right-3 top-2 cursor-pointer text-gray-500"
              >
                {showPassword ? (
                  <AiFillEye size={24} />
                ) : (
                  <AiFillEyeInvisible size={24} />
                )}
              </span>
              {errors.password && (
                <p className="text-red-500 text-sm mt-1">{errors.password}</p>
              )}
            </div>

            <div className="flex items-center justify-between mb-6">
              <div className="flex items-center justify-center">
                <input type="checkbox" id="remember" className="mr-2 w-4 h-4" />
                <label htmlFor="remember" className="text-gray-700 text-sm">
                  Recordarme
                </label>
              </div>
              <a href="#" className="text-[#F44336] text-sm hover:underline">
                ¿Olvidaste tu contraseña?
              </a>
            </div>
            <button
              type="submit"
              className="w-full bg-[#1579B0] text-white py-2 rounded-md hover:bg-blue-700 transition"
            >
              Ingresar
            </button>
          </form>
          <div className="mt-4 text-center">
            <p className="text-gray-600 text-sm">
              ¿No tienes una cuenta?{" "}
              <a href="#" className="text-[#1579B0] hover:underline">
                Regístrate
              </a>
            </p>
          </div>
          <div className="mt-10 text-center">
            <div className="flex items-center justify-center">
              <div className="flex-grow border-t border-gray-300"></div>
              <p className="text-gray-400 text-sm mx-4">O ingresa con</p>
              <div className="flex-grow border-t border-gray-300"></div>
            </div>
            <div className="flex justify-center space-x-4 mt-10">
              <button className="flex items-center justify-center w-[160px] h-[56px] p-2 rounded-md hover:bg-gray-300 border border-[#515DEF]">
                <Image
                  src="/login/facebook.png"
                  alt="Facebook"
                  width={24}
                  height={24}
                />
              </button>
              <button className="flex items-center justify-center w-[160px] h-[56px] p-2 rounded-md hover:bg-gray-300 border border-[#515DEF]">
                <Image
                  src="/login/google.png"
                  alt="Google"
                  width={24}
                  height={24}
                />
              </button>
              <button className="flex items-center justify-center w-[160px] h-[56px] p-2 rounded-md hover:bg-gray-300 border border-[#515DEF]">
                <Image
                  src="/login/apple.png"
                  alt="Apple"
                  width={24}
                  height={24}
                />
              </button>
            </div>
          </div>
        </div>

        {/* Right Side - Illustration */}
        <div className="hidden md:flex w-1/2 justify-center items-center">
          <Image
            src="/login/imagen1.png"
            alt="Illustration"
            width={672}
            height={672}
          />
        </div>
      </div>
    </div>
  );
};

export default SignIn;
