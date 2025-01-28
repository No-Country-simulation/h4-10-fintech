'use client';

import React from 'react';
import Image from 'next/image';

const ForgotPassword: React.FC = () => {
    return (
        <div className="flex items-center justify-center min-h-screen bg-white">
            {/* Contenedor Principal */}
            <div className="flex flex-col-reverse lg:flex-row items-center max-w-6xl mx-auto px-4 lg:px-8">
                {/* Izquierda */}
                <div className="w-full lg:w-1/2">
                    <a href="/" className="text-yellow-500 font-bold text-xl">
                        AGREGAR LOGO
                    </a>
                    <div className="mt-6">
                        <a href="/login" className="text-blue-600 text-sm hover:underline">
                            &lt; Regresar a iniciar sesión
                        </a>
                        <h1 className="text-3xl font-semibold mt-4">¿Olvidaste tu contraseña?</h1>
                        <p className="text-gray-600 mt-2">
                            No te preocupes, nos pasa a todos. Ingresa el correo electrónico asociado a tu cuenta.
                        </p>
                    </div>
                    <form className="mt-6">
                        <label
                            htmlFor="email"
                            className="block text-sm font-medium text-gray-700"
                        >
                            Email
                        </label>
                        <input
                            type="email"
                            id="email"
                            placeholder="camila@email.com"
                            className="block w-full px-3 py-2 mt-2 text-gray-900 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                        <button
                            type="submit"
                            className="w-full bg-blue-600 text-white py-2 px-4 mt-4 rounded-lg hover:bg-blue-700 transition"
                        >
                            Enviar
                        </button>
                    </form>
                    <div className="flex items-center mt-6">
                        <div className="flex-grow border-t border-gray-300"></div>
                        <p className="mx-4 text-sm text-gray-500">O ingresa con</p>
                        <div className="flex-grow border-t border-gray-300"></div>
                    </div>
                    <div className="flex justify-center space-x-4 mt-10">
                                        <button className="flex items-center justify-center w-[160px] h-[56px] p-2 rounded-md hover:bg-gray-300 border border-[#515DEF]">
                                            <Image src="/login/facebook.png" alt="Facebook" width={24} height={24} />
                                        </button>
                                        <button className="flex items-center justify-center w-[160px] h-[56px] p-2 rounded-md hover:bg-gray-300 border border-[#515DEF]">
                                            <Image src="/login/google.png" alt="Google" width={24} height={24} />
                                        </button>
                                        <button className="flex items-center justify-center w-[160px] h-[56px] p-2 rounded-md hover:bg-gray-300 border border-[#515DEF]">
                                            <Image src="/login/apple.png" alt="Apple" width={24} height={24} />
                                        </button>
                                    </div>
                </div>
                {/* Derecha */}
                <div className="hidden md:flex w-1/2 justify-center items-center">
                                <Image
                                    src="/you-forgot/you-forgot.png"
                                    alt="Illustration"
                                    width={600}
                                    height={600}
                                    className="max-w-md"
                                />
                            </div>
            </div>
        </div>
    );
};

export default ForgotPassword;
