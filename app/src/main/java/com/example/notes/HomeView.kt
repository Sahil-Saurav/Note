package com.example.notes

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.notes.Data.DummyData
import com.example.notes.Data.Note
import kotlinx.coroutines.launch

@Composable
fun HomeView(viewModel: NoteViewModel, navController: NavController) {


    var showAlert by remember { mutableStateOf(false) }
    var noteToDelete by remember { mutableStateOf<Note?>(null) }


    Scaffold(topBar = { TopBar(title = stringResource(id = R.string.home_bar), onNavClicked = {}) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.AddEditScreen.route) },
                backgroundColor = colorResource(id = R.color.primary),
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note", tint = Color.White)
            }
        }
    ) {
        val notes = viewModel.getAllNote.collectAsState(initial = listOf())
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(colorResource(id = R.color.background))
        ) {
            items(notes.value, key = { note -> note.id }) { note ->
                NoteItem(note = note, onDelClick = {
                    noteToDelete = note
                    showAlert = true
                }, onUpdateClick = {
                    val id = note.id.toString()
                    val title = note.title
                    val desc = note.desc
                    navController.navigate(Screens.UpdateScreen.route+"/$id/$title/$desc")})

                {
                    val title = note.title
                    val desc = note.desc
                    navController.navigate(Screens.ViewScreen.route + "/$title/$desc")
                }
            }
        }
    }
        if (showAlert) {
            AlertDialog(
                onDismissRequest = { showAlert = false },
                backgroundColor = colorResource(id = R.color.background),
                title = {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = colorResource(id = R.color.head_font),
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Confirm Delete:",
                            color = colorResource(id = R.color.head_font),
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp
                        )
                    }
                },
                text = {
                    Text(
                        text = "Your note will be permanently deleted",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.body_font)
                    )
                },
                shape = RoundedCornerShape(20),
                buttons = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                showAlert = false
                                noteToDelete?.let { viewModel.deleteNote(it) }
                                noteToDelete = null
                            },
                            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.button))
                        ) {
                            Text(text = "Yes")
                        }
                        Button(
                            onClick = {
                                showAlert = false
                                noteToDelete = null
                            },
                            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.button))
                        ) {
                            Text(text = "No")
                        }
                    }
                }
            )
        }
    }

@Composable
fun NoteItem(note: Note, onDelClick: () -> Unit,onUpdateClick:()->Unit,onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary)),
        elevation = CardDefaults.cardElevation(15.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxSize()
        ) {
            Text(
                text = note.title,
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                maxLines = 1,
                color = colorResource(id = R.color.card_primary_font)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = note.desc,
                maxLines = 4,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { onDelClick() }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = colorResource(id = R.color.card_primary_font)
                )
            }
            IconButton(onClick = {onUpdateClick()}) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Update",
                    tint = colorResource(id = R.color.card_primary_font))
            }
        }
    }
}

