import React from "react";
import Image from "next/image";
import candadoImage from "@/assets/candado.png";
import recomendaImage from "@/assets/recomenda.png";
import castImage from "@/assets/cast.png";
import seguimientoImage from "@/assets/seguimiento.png";
import asesoramientoImage from "@/assets/asesoramiento.png";
import ahorroImage from "@/assets/ahorro.png";

const Choose = () => {
    return (
        <main className="text-white p-3">
            <h1 className="text-4xl font-bold text-left mb-14">¿Por qué elegir iUPi?</h1>
            <div className="grid grid-cols-4 gap-5">
                {/* Columna 1 */}
                <div className="">
                    <h2 className="text-xl font-bold mb-3 flex items-center space-x-2">
                        <Image src={candadoImage} alt="icon" width={24} height={24} className="flex-shrink-0" />
                        <span>Seguridad y privacidad garantizada.</span></h2>
                    <p className="">
                        Tus datos están protegidos con los más altos estándares. Confía en una
                        aplicación diseñada para cuidar tu información financiera.
                    </p>
                </div>

                {/* Columna 2 */}
                <div className="">
                    <h2 className="text-xl font-bold mb-3 flex items-center space-x-2">
                    <Image src={recomendaImage} alt="icon" width={36} height={36} className="flex-shrink-0" />
                        <span>Recomendaciones de inversión personalizadas.</span>
                    </h2>
                    <p>
                        Basadas en tu perfil financiero.
                    </p>
                </div>

                {/* Columna 3 */}
                <div className="">
                    <h2 className="text-xl font-bold mb-3 flex items-center space-x-2">
                    <Image src={castImage} alt="icon" width={36} height={36} className="flex-shrink-0" />
                        <span>Educación financiera al alcance de todos.</span>
                    </h2>
                    <p>
                        Accede a nuestros recursos disponibles para comenzar a invertir 
                        de manera mas segura e inteligente.
                    </p>
                </div>

                {/* Columna 4 */}
                <div className="">
                    <h2 className="text-xl font-bold mb-3 flex items-center space-x-2">
                    <Image src={seguimientoImage} alt="icon" width={24} height={24} className="flex-shrink-0" />
                        <span>Seguimiento de metas en tiepo real.</span></h2>
                    <p>
                        Podrás visualizar tu progreso diario para alcanzar tus objetivos.
                    </p>
                </div>
            </div>
            <div className="grid grid-cols-2 mt-14">
                <div>
                    <h2 className="text-xl font-bold mb-3 flex items-center space-x-2">
                    <Image src={asesoramientoImage} alt="icon" width={24} height={24} className="flex-shrink-0" />
                        <span>Asesoramiento confiable</span></h2>
                    <p>
                        Recibe las mejores recomendaciones de inversión de nuestras notificaciones
                        personalizadas.
                    </p>
                </div>
                <div>
                    <h2 className="text-xl font-bold mb-3 flex items-center space-x-2">
                    <Image src={ahorroImage} alt="icon" width={24} height={24} className="flex-shrink-0" />
                        <span>Ahorro de tiempo y esfuerzo.</span></h2>
                    <p>
                        Todas las oportunidades de inversión en un solo lugar.
                    </p>
                </div>

            </div>
        </main>
    );
};

export default Choose;
