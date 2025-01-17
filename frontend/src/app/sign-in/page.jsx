import React from 'react';
import Image from 'next/image';

const SignIn = () => {
    return (
        <div className="flex h-screen w-screen items-center justify-center bg-white">
            <div className="w-full h-full flex flex-col md:flex-row  overflow-hidden">
                {/* Left Side - Form */}
                <div className="w-full md:w-1/2 p-10 mt-24 lg:max-w-xl lg:flex flex-col">
                    <h2 className="text-2xl font-bold text-gray-800">Iniciar sesión</h2>
                    <p className="text-gray-600 mt-2">Inicia sesión para acceder a tu cuenta IUPI</p>
                    <form className="mt-6">
                        <div className="mb-4">
                            <label htmlFor="email" className="block text-gray-700">E-mail</label>
                            <input
                                type="email"
                                id="email"
                                placeholder="john.doe@gmail.com"
                                className="w-full border border-gray-300 rounded-md p-2 mt-1 focus:outline-none focus:ring focus:ring-blue-300"
                            />
                        </div>
                        <div className="mb-4">
                            <label htmlFor="password" className="block text-gray-700">Password</label>
                            <div className="relative">
                                <input
                                    type="password"
                                    id="password"
                                    placeholder="********"
                                    className="w-full border border-gray-300 rounded-md p-2 mt-1 focus:outline-none focus:ring focus:ring-blue-300"
                                />
                                <button
                                    type="button"
                                    className="absolute right-2 top-3 text-gray-500"
                                    aria-label="Mostrar contraseña"
                                >
                                    👁️
                                </button>
                            </div>
                        </div>
                        <div className="flex items-center justify-between mb-4">
                            <div className="flex items-center justify-center ">
                                <input type="checkbox" id="remember" className="mr-2" />
                                <label htmlFor="remember" className="text-gray-700">Recordarme</label>
                            </div>
                            <a href="#" className="text-[#F44336] text-sm hover:underline">¿Olvidaste tu contraseña?</a>
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
                            ¿No tienes una cuenta? <a href="#" className="text-[#1579B0] hover:underline">Regístrate</a>
                        </p>
                    </div>
                    <div className="mt-10 text-center">
                        <div className="flex items-center justify-center">
                            <div className="flex-grow border-t border-gray-300"></div>
                            <p className="text-gray-600 text-sm mx-4">O ingresa con</p>
                            <div className="flex-grow border-t border-gray-300"></div>
                        </div>
                        <div className="flex justify-center space-x-4 mt-10">
                            <button className="flex items-center justify-center w-[160px] h-[56px] p-2 rounded-md hover:bg-gray-300 border border-[#515DEF]">
                                <img src="/login/facebook.png" alt="Facebook" className="h-6 w-6" />
                            </button>
                            <button className="flex items-center justify-center w-[160px] h-[56px] p-2 rounded-md hover:bg-gray-300 border border-[#515DEF]">
                                <img src="/login/google.png" alt="Google" className="h-6 w-6" />
                            </button>
                            <button className="flex items-center justify-center w-[160px] h-[56px] p-2 rounded-md hover:bg-gray-300 border border-[#515DEF]">
                                <img src="/login/apple.png" alt="Apple" className="h-6 w-6" />
                            </button>
                        </div>
                    </div>
                </div>

                {/* Right Side - Illustration */}
                <div className="hidden md:flex w-1/2 justify-center items-center">
                    <Image src="/login/imagen1.png" alt="Illustration" width={672} height={672} />
                </div>
            </div>
        </div>
    );
};

export default SignIn;
