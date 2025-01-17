
'use client';
import { useState } from "react";
import Image from "next/image";

interface FormData {
    nombre: string;
    apellido: string;
    email: string;
    phone: string;
    password: string;
    confirmPass: string;
}

interface FormErrors {
    nombre?: string;
    apellido?: string;
    email?: string;
    phone?: string;
    password?: string;
    confirmPass?: string;
}

const Register = () => {
    const [formData, setFormData] = useState<FormData>({
        nombre: "",
        apellido: "",
        email: "",
        phone: "",
        password: "",
        confirmPass: "",
    });

    const [errors, setErrors] = useState<FormErrors>({});

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { id, value } = e.target;
        setFormData({
            ...formData,
            [id]: value,
        });
        setErrors({
            ...errors,
            [id]: "",
        });
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        const newErrors: FormErrors = {};

        // Validar campos vacíos
        Object.keys(formData).forEach((key) => {
            if (!formData[key as keyof FormData]) {
                newErrors[key as keyof FormData] = "Este campo es obligatorio.";
            }
        });

        // Validar coincidencia de contraseñas
        if (formData.password !== formData.confirmPass) {
            newErrors.confirmPass = "Las contraseñas no coinciden.";
        }

        if (Object.keys(newErrors).length > 0) {
            setErrors(newErrors);
        } else {
            console.log("Formulario enviado:", formData);
            alert("Formulario enviado con éxito.");
            // Aquí puedes manejar el envío de datos al servidor
        }
    };

    return (
        <div className="flex flex-col lg:flex-row items-center justify-center min-h-screen bg-white">
            {/* Izquierda: Formulario */}
            <div className="lg:w-1/2 px-8 md:px-16">
                <h1 className="text-4xl font-bold text-gray-800">Registrarse</h1>
                <p className="text-gray-600 mt-4">
                    ¿Sos nuevo en iUPi? Crea tu cuenta en pocos pasos y comenzá a ahorrar.
                </p>

                {/* Formulario */}
                <form className="mt-8 space-y-6" onSubmit={handleSubmit}>
                    {/* Grupo flotante */}
                    <div className="flex gap-5">
                        <div className="relative w-full">
                            <label
                                htmlFor="nombre"
                                className="absolute text-sm left-3 top-[-10px] bg-white px-1"
                            >
                                Nombre
                            </label>
                            <input
                                type="text"
                                id="nombre"
                                value={formData.nombre}
                                onChange={handleChange}
                                className="w-full border border-[#79747E] rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                            />
                            {errors.nombre && (
                                <p className="text-red-500 text-xs mt-1">{errors.nombre}</p>
                            )}
                        </div>

                        <div className="relative w-full">
                            <label
                                htmlFor="apellido"
                                className="absolute text-sm left-3 top-[-10px] bg-white px-1"
                            >
                                Apellido
                            </label>
                            <input
                                type="text"
                                id="apellido"
                                value={formData.apellido}
                                onChange={handleChange}
                                className="w-full border border-[#79747E] rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                            />
                            {errors.apellido && (
                                <p className="text-red-500 text-xs mt-1">{errors.apellido}</p>
                            )}
                        </div>
                    </div>

                    <div className="flex gap-5">
                        <div className="relative w-full">
                            <label
                                htmlFor="email"
                                className="absolute text-sm left-3 top-[-10px] bg-white px-1"
                            >
                                Correo electrónico
                            </label>
                            <input
                                type="email"
                                id="email"
                                value={formData.email}
                                onChange={handleChange}
                                className="w-full border border-[#79747E] rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                            />
                            {errors.email && (
                                <p className="text-red-500 text-xs mt-1">{errors.email}</p>
                            )}
                        </div>
                        <div className="relative w-full">
                            <label
                                htmlFor="phone"
                                className="absolute text-sm left-3 top-[-10px] bg-white px-1"
                            >
                                Número de teléfono
                            </label>
                            <input
                                type="number"
                                id="phone"
                                value={formData.phone}
                                onChange={handleChange}
                                className="w-full border border-[#79747E] rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                            />
                            {errors.phone && (
                                <p className="text-red-500 text-xs mt-1">{errors.phone}</p>
                            )}
                        </div>
                    </div>

                    {/* Campos adicionales */}
                    <div className="relative w-full">
                        <label
                            htmlFor="password"
                            className="absolute text-sm left-3 top-[-10px] bg-white px-1"
                        >
                            Contraseña
                        </label>
                        <input
                            type="password"
                            id="password"
                            value={formData.password}
                            onChange={handleChange}
                            className="w-full border border-[#79747E] rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                        {errors.password && (
                            <p className="text-red-500 text-xs mt-1">{errors.password}</p>
                        )}
                    </div>
                    <div className="relative w-full">
                        <label
                            htmlFor="confirmPass"
                            className="absolute text-sm left-3 top-[-10px] bg-white px-1"
                        >
                            Confirmar contraseña
                        </label>
                        <input
                            type="password"
                            id="confirmPass"
                            value={formData.confirmPass}
                            onChange={handleChange}
                            className="w-full border border-[#79747E] rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                        {errors.confirmPass && (
                            <p className="text-red-500 text-xs mt-1">{errors.confirmPass}</p>
                        )}
                    </div>

                    <button
                        type="submit"
                        className="w-full bg-[#515DEF] text-white py-3 rounded-md hover:bg-blue-700 transition"
                    >
                        Crear cuenta
                    </button>
                </form>
            </div>

            {/* Derecha: Imagen */}
            <div className="hidden lg:flex lg:w-1/2 items-center justify-center">
                <Image
                    src="/register/imagen.png"
                    alt="Illustration"
                    width={548}
                    height={548}
                    className="max-w-md"
                />
            </div>
        </div>
    );
};

export default Register;
