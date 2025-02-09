package com.gondroid.noteai.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gondroid.noteai.domain.Category
import com.gondroid.noteai.domain.Note
import com.gondroid.noteai.ui.theme.NoteAppTheme
import kotlin.random.Random

@Composable
@Preview(showBackground = true)
fun NoteItemPreview() {
    NoteAppTheme {
        NoteItem(
            modifier = Modifier,
            onItemSelected = {
            },
            note = Note(
                id = "1",
                title = "Project Deadline",
                content = "Submit project report by Friday.",
                category = Category.WORK
            )
        )
    }
}

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    onItemSelected: () -> Unit,
    note: Note
) {

    val randomHeight = remember { Random.nextInt(100, 300) } // Altura aleatoria entre 100 y 300 dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(randomHeight.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(randomColor())
            .padding(16.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(randomHeight.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis, // Hol..
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )

            Text(
                text = note.content,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis, // Hol..
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )

            Spacer(Modifier.weight(1f))
        }
        Text(
            text = note.category?.name ?: "",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.align(Alignment.BottomEnd).clip(RoundedCornerShape(8.dp))
                .background(randomColor())
        )
    }
}

fun randomColor(): Color {
    return Color(
        red = Random.nextFloat(),
        green = Random.nextFloat(),
        blue = Random.nextFloat(),
        alpha = 1f
    )
}