import { PersonalizedNews } from "@/app/dashboard/components/personalized-news"
import { DailyTip } from "@/app/dashboard/components/daily-tip"
import { InteractiveForum } from "@/app/dashboard/components/interactive-forum"

export default function CommunityPage() {
    return (
        <div className="space-y-6">
            <h1 className="text-2xl font-bold">Comunidad y Noticias</h1>
            <div className="grid gap-6 md:grid-cols-2">
                <PersonalizedNews />
                <DailyTip />
            </div>
            <InteractiveForum />
        </div>
    )
}

