package com.gondroid.noteai.presentation.screens.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gondroid.noteai.R
import com.gondroid.noteai.domain.Category
import com.gondroid.noteai.domain.Note
import com.gondroid.noteai.presentation.screens.home.components.NoteItem
import com.gondroid.noteai.ui.theme.NoteAppTheme

@Composable
fun NotesScreenRoot(
    viewModel: NotesViewModel
) {
    val state = viewModel.state
    val event = viewModel.events
    val context = LocalContext.current


    NotesScreen(
        state = state,
        onAction = { }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    state: NotesDataState,
    onAction: (NotesScreenAction) -> Unit
) {
    var isMenuExtended by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notes.AI",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {

                            }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Add Task",
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                        DropdownMenu(
                            expanded = isMenuExtended,
                            modifier = Modifier.background(
                                color = MaterialTheme.colorScheme.surfaceContainerHighest
                            ),
                            onDismissRequest = { isMenuExtended = false }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = stringResource(R.string.delete_all),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                },
                                onClick = {
                                    isMenuExtended = false
                                }
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Task",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
        }
    ) { paddingValues ->

        val items = listOf(
            Note(
                id = "1",
                title = "Project Deadline",
                content = "Submit project report by Friday. Submit project report by Friday. Submit project report by Friday. Submit project report by Friday.",
                category = Category.WORK
            ),
            Note(
                id = "2",
                title = "Grocery List",
                content = "Milk, eggs, bread, eggs, bread, and coffee.",
                category = Category.PERSONAL
            ),
            Note(
                id = "3",
                title = "Meeting Notes",
                content = "Discuss app redesign with the team.",
                category = Category.WORK
            ),
            Note(
                id = "4",
                title = "Book Recommendation",
                content = "Read 'Atomic Habits' by James Clear.",
                category = Category.LEARNING
            ),
            Note(
                id = "5",
                title = "Workout Plan",
                content = "Cardio on Monday, Strength on Tuesday.",
                category = Category.HEALTH
            ),
            Note(
                id = "6",
                title = "Birthday Reminder",
                content = "Buy a gift for Sarah's birthday.",
                category = Category.PERSONAL
            ),
            Note(
                id = "7",
                title = "App Idea",
                content = "A habit tracker with AI recommendations.",
                category = Category.WORK
            ),
            Note(
                id = "8",
                title = "Travel Checklist",
                content = "Passport, tickets, charger, and toiletries.",
                category = Category.PERSONAL
            ),
            Note(
                id = "9",
                title = "Course Notes",
                content = "Key takeaways from the Kotlin coroutines course.",
                category = Category.LEARNING
            ),
            Note(
                id = "10",
                title = "Budget Plan",
                content = "Track monthly expenses and savings.",
                category = Category.FINANCE
            ),
            Note(
                id = "11",
                title = "Travel Checklist",
                content = "Passport, tickets, charger, and toiletries.",
                category = Category.PERSONAL
            ),
            Note(
                id = "12",
                title = "Course Notes",
                content = "Key takeaways from the Kotlin coroutines course.",
                category = Category.LEARNING
            ),
            Note(
                id = "13",
                title = "Budget Plan",
                content = "Track monthly expenses and savings.",
                category = Category.FINANCE
            )
        )


        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(
                items = items,
            ) { note ->
                NoteItem(
                    modifier = Modifier,
                    onItemSelected = { },
                    note = note
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NoteAppTheme {
        NotesScreen(
            state = NotesDataState(),
            onAction = {
            }
        )
    }
}
