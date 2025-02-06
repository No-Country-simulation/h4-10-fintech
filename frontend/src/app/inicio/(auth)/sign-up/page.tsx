'use client';
import { useState } from "react";
import Image from "next/image";
import { AiFillEye, AiFillEyeInvisible } from 'react-icons/ai';

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
    const [showPassword, setShowPassword] = useState<boolean>(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState<boolean>(false);

const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { id, value } = e.target;

    setFormData({
        ...formData,
        [id]: value,
    });

    setErrors((prevErrors) => ({
        ...prevErrors,
        [id]: "",
    }));
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
    }
};


    return (
        <div className="flex flex-col lg:flex-row items-center justify-center min-h-screen bg-white">
            {/* Izquierda: Formulario */}
            <div className="lg:w-1/2 px-8 md:px-16 p-3">
                <div className='w-full'>
                    <Image src='/logo.png' alt='iUPi' layout="responsive" width={497} height={117} className="object-cover" />
                </div>
                <h1 className="text-4xl font-bold text-gray-800">Registrarse</h1>
                <p className="text-gray-600 mt-4">
                    ¿Sos nuevo en iUPi? Crea tu cuenta en pocos pasos y comenzá a ahorrar.
                </p>

                {/* Formulario */}
                <form className="mt-8 space-y-6" onSubmit={handleSubmit}>
                    <div className="flex gap-5">
                        <div className="relative w-full">
                            <label htmlFor="nombre" className="absolute text-sm left-3 top-[-10px] bg-white px-1">Nombre</label>
                            <input
                                type="text"
                                id="nombre"
                                value={formData.nombre}
                                onChange={handleChange}
                                className="w-full border border-[#79747E] rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                            />
                            {errors.nombre && <p className="text-red-500 text-xs mt-1">{errors.nombre}</p>}
                        </div>

                        <div className="relative w-full">
                            <label htmlFor="apellido" className="absolute text-sm left-3 top-[-10px] bg-white px-1">Apellido</label>
                            <input
                                type="text"
                                id="apellido"
                                value={formData.apellido}
                                onChange={handleChange}
                                className="w-full border border-[#79747E] rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                            />
                            {errors.apellido && <p className="text-red-500 text-xs mt-1">{errors.apellido}</p>}
                        </div>
                    </div>

                    <div className="flex gap-5">
                        <div className="relative w-full">
                            <label htmlFor="email" className="absolute text-sm left-3 top-[-10px] bg-white px-1">Correo electrónico</label>
                            <input
                                type="email"
                                id="email"
                                value={formData.email}
                                onChange={handleChange}
                                className="w-full border border-[#79747E] rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                            />
                            {errors.email && <p className="text-red-500 text-xs mt-1">{errors.email}</p>}
                        </div>
                        <div className="relative w-full">
                            <label htmlFor="phone" className="absolute text-sm left-3 top-[-10px] bg-white px-1">Número de teléfono</label>
                            <input
                                type="number"
                                id="phone"
                                value={formData.phone}
                                onChange={handleChange}
                                className="w-full border border-[#79747E] rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                            />
                            {errors.phone && <p className="text-red-500 text-xs mt-1">{errors.phone}</p>}
                        </div>
                    </div>

                    {/* Contraseña */}
                    <div className="relative w-full">
                        <label htmlFor="password" className="absolute text-sm left-3 top-[-10px] bg-white px-1">Contraseña</label>
                        <input
                            type={showPassword ? "text" : "password"}
                            id="password"
                            value={formData.password}
                            onChange={handleChange}
                            className="w-full border border-[#79747E] rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                        <span
                            onClick={() => setShowPassword(!showPassword)}
                            className="absolute right-3 top-3 cursor-pointer text-gray-500"
                        >
                            {showPassword ? <AiFillEye size={20} /> : <AiFillEyeInvisible size={20} />}
                        </span>
                        {errors.password && <p className="text-red-500 text-xs mt-1">{errors.password}</p>}
                    </div>

                    <div className="relative w-full">
                        <label htmlFor="confirmPass" className="absolute text-sm left-3 top-[-10px] bg-white px-1">Confirmar contraseña</label>
                        <input
                            type={showConfirmPassword ? "text" : "password"}
                            id="confirmPass"
                            value={formData.confirmPass}
                            onChange={handleChange}
                            className="w-full border border-[#79747E] rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                        <span
                            onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                            className="absolute right-3 top-3 cursor-pointer text-gray-500"
                        >
                            {showConfirmPassword ? <AiFillEye size={20} /> : <AiFillEyeInvisible size={20} />}
                        </span>
                        {errors.confirmPass && <p className="text-red-500 text-xs mt-1">{errors.confirmPass}</p>}
                    </div>

                    <button
                        type="submit"
                        className="w-full bg-[#515DEF] text-white py-3 rounded-md hover:bg-blue-700 transition"
                    >
                        Crear cuenta
                    </button>
                </form>
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

            <div className="hidden md:flex w-1/2 justify-center items-center">
                <Image
                    src="/register/imagen.png"
                    alt="Illustration"
                    width={600}
                    height={600}
                    className="max-w-md"
                />
            </div>
        </div>
    );
};

export default Register;
