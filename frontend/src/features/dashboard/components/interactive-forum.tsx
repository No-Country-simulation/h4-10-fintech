"use client"

import { useState } from "react"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Textarea } from "@/components/ui/textarea"
import { Avatar, AvatarFallback } from "@/components/ui/avatar"
import { MessageSquare, ThumbsUp } from 'lucide-react'

// Simulación de posts del foro
const initialPosts = [
    { id: 1, author: "MariaInvest", content: "¿Alguien más está considerando invertir en energías renovables? Parece ser un sector prometedor.", likes: 5, replies: 2 },
    { id: 2, author: "CarlosFinanzas", content: "Acabo de leer sobre los ETFs. ¿Alguien tiene experiencia con ellos? ¿Recomendaciones?", likes: 3, replies: 1 },
]

export function InteractiveForum() {
    const [posts, setPosts] = useState(initialPosts)
    const [newPost, setNewPost] = useState("")

    const handlePostSubmit = (e: React.FormEvent) => {
        e.preventDefault()
        if (newPost.trim()) {
            setPosts([
                { id: posts.length + 1, author: "Usuario", content: newPost, likes: 0, replies: 0 },
                ...posts
            ])
            setNewPost("")
        }
    }

    return (
        <Card className="bg-white dark:bg-gray-800 shadow-sm">
            <CardHeader>
                <CardTitle className="text-lg font-semibold flex items-center">
                    <MessageSquare className="mr-2 h-5 w-5" />
                    Foro Interactivo
                </CardTitle>
            </CardHeader>
            <CardContent>
                <form onSubmit={handlePostSubmit} className="mb-6">
                    <Textarea
                        placeholder="Comparte tu opinión o pregunta..."
                        value={newPost}
                        onChange={(e) => setNewPost(e.target.value)}
                        className="mb-2"
                    />
                    <Button type="submit">Publicar</Button>
                </form>
                <div className="space-y-4">
                    {posts.map((post) => (
                        <div key={post.id} className="border-b border-gray-200 dark:border-gray-700 pb-4 last:border-b-0">
                            <div className="flex items-start space-x-3">
                                <Avatar>
                                    <AvatarFallback>{post.author[0]}</AvatarFallback>
                                </Avatar>
                                <div className="flex-1">
                                    <p className="font-medium">{post.author}</p>
                                    <p className="text-gray-600 dark:text-gray-300 mt-1">{post.content}</p>
                                    <div className="flex items-center space-x-4 mt-2">
                                        <Button variant="ghost" size="sm" className="text-gray-500 hover:text-gray-700">
                                            <ThumbsUp className="h-4 w-4 mr-1" />
                                            {post.likes}
                                        </Button>
                                        <Button variant="ghost" size="sm" className="text-gray-500 hover:text-gray-700">
                                            <MessageSquare className="h-4 w-4 mr-1" />
                                            {post.replies}
                                        </Button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </CardContent>
        </Card>
    )
}

