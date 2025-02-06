import React from 'react';
import Image from 'next/image';

// Imágenes de ejemplo
import Step1Image from '@/assets/step1.png';
import Step2Image from '@/assets/step2.png';
import Step3Image from '@/assets/step3.png';
import Step4Image from '@/assets/step4.png';
import Step5Image from '@/assets/step5.png';

const steps = [
    {
        id: 1,
        title: 'Paso 1: Descarga la aplicación iUPi.',
        description: 'Busca nuestra app iUPi en Google Play o App Store y descárgala.',
        image: Step1Image,
    },
    {
        id: 2,
        title: 'Paso 2: Crea tu cuenta.',
        description: 'Regístrate de manera sencilla para comenzar a disfrutar de los beneficios que iUPi tiene para vos.',
        image: Step2Image,
    },
    {
        id: 3,
        title: 'Paso 3: Responde unas preguntas rápidas.',
        description: 'Contanos sobre vos para ofrecerte una excelente experiencia financiera.',
        image: Step3Image,
    },
    {
        id: 4,
        title: 'Paso 4: Descubre tu perfil financiero.',
        description: 'Accedé a un análisis completo sobre tu perfil financiero.',
        image: Step4Image,
    },
    {
        id: 5,
        title: 'Paso 5: Comenzá a invertir con confianza.',
        description: '¡Tomá el control de tus finanzas a través de nuestras recomendaciones diarias!',
        image: Step5Image,
    },
];

const Steps: React.FC = () => {
    return (
        <div className="max-w-3xl mx-auto p-6 mt-4">
            <div className="mx-auto p-4 mb-4 flex justify-center items-center">
                <h2 className="text-4xl font-semibold text-center">
                    ¿Cómo? Sencillo, seguí los siguientes pasos:
                </h2>
            </div>
            <div className="space-y-10 mt-8">
                {steps.map((step) => (
                    <div key={step.id} className="flex items-start space-x-4">
                        <div className="w-24 h-24 flex-shrink-0">
                            <Image
                                src={step.image}
                                alt={step.title}
                                className="rounded-lg object-cover w-full h-full"
                            />
                        </div>
                        <div>
                            <h3 className="text-2xl font-bold mb-1">{step.title}</h3>
                            <p className="text-lg text-gray-600">{step.description}</p>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Steps;
