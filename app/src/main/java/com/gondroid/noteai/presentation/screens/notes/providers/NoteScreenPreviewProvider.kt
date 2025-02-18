package com.gondroid.noteai.presentation.screens.notes.providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.gondroid.noteai.domain.Category
import com.gondroid.noteai.domain.Note
import com.gondroid.noteai.presentation.screens.notes.NoteDataState

class NoteScreenPreviewProvider : PreviewParameterProvider<NoteDataState> {
    override val values: Sequence<NoteDataState>
        get() = sequenceOf(
            NoteDataState(
                date = "March 29, 2024",
                notes = notes
            )
        )
}


val notes = mutableListOf<Note>()
    .apply {
        addAll(
            arrayOf(
                Note(
                    id = "1",
                    title = "Project Deadline",
                    content = "Submit project report by Friday. Submit project report by Friday. Submit project report by Friday. Submit project report by Friday.",
                    category = Category.WORK.toString()
                ),
                Note(
                    id = "2",
                    title = "Grocery List",
                    content = "Milk, eggs, bread, eggs, bread, and coffee.",
                    category = Category.PERSONAL.toString()
                ),
                Note(
                    id = "3",
                    title = "Resumen de Attack on Titan (Shingeki no Kyojin)",
                    content = "La humanidad vive dentro de enormes murallas para protegerse de los titanes, gigantes devoradores de humanos. Eren Jaeger, junto a sus amigos Mikasa y Armin, presencia la destrucción de su hogar cuando el Titán Colosal abre una brecha en la Muralla María. Motivado por la venganza, Eren se une al Cuerpo de Exploración para luchar contra los titanes y descubrir la verdad detrás de su existencia.\n" +
                            "\n" + "A lo largo de la historia, se revelan secretos sobre los titanes, las murallas y la verdadera historia del mundo. Traiciones, conspiraciones y batallas épicas llevan a Eren a tomar decisiones que cambiarán el destino de la humanidad.\n" +
                            "\n" + "Es una historia llena de acción, drama y giros impactantes que explora la libertad, la guerra y la lucha por la supervivencia",
                    category = Category.WORK.toString()
                ),
                Note(
                    id = "4",
                    title = "Book Recommendation",
                    content = "Read 'Atomic Habits' by James Clear.",
                    category = Category.LEARNING.toString()
                ),
                Note(
                    id = "5",
                    title = "Workout Plan",
                    content = "Cardio on Monday, Strength on Tuesday.",
                    category = Category.HEALTH.toString()
                ),
                Note(
                    id = "6",
                    title = "Birthday Reminder",
                    content = "Buy a gift for Sarah's birthday.",
                    category = Category.PERSONAL.toString()
                ),
                Note(
                    id = "7",
                    title = "App Idea",
                    content = "A habit tracker with AI recommendations.",
                    category = Category.WORK.toString()
                ),
                Note(
                    id = "8",
                    title = "Travel Checklist",
                    content = "Passport, tickets, charger, and toiletries.",
                    category = Category.PERSONAL.toString()
                ),
                Note(
                    id = "9",
                    title = "Course Notes",
                    content = "Key takeaways from the Kotlin coroutines course.",
                    category = Category.LEARNING.toString()
                ),
                Note(
                    id = "10",
                    title = "Budget Plan",
                    content = "Track monthly expenses and savings.",
                    category = Category.FINANCE.toString()
                ),
                Note(
                    id = "11",
                    title = "Travel Checklist",
                    content = "Passport, tickets, charger, and toiletries.",
                    category = Category.PERSONAL.toString()
                ),
                Note(
                    id = "12",
                    title = "Course Notes",
                    content = "Key takeaways from the Kotlin coroutines course.",
                    category = Category.LEARNING.toString()
                ),
                Note(
                    id = "13",
                    title = "Budget Plan",
                    content = "Track monthly expenses and savings.",
                    category = Category.FINANCE.toString()
                )
            )
        )
    }