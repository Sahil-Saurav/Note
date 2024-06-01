package com.example.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notes.Data.Note
import kotlinx.coroutines.launch

@Composable
fun UpdateScreen(id:String,title:String,desc:String,viewModel: NoteViewModel,navController: NavController){
    val id :Long = id.toLong()
    title.let { viewModel.titleState=it}
    desc.let { viewModel.descState=it}
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(topBar = { TopBar(
        title = stringResource(id = R.string.update_note),
        onNavClicked = {navController.navigateUp()})
    },  snackbarHost = { SnackbarHost(snackBarHostState)},
        modifier = Modifier.background((colorResource(id = R.color.background)))) {
        Column(modifier = Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
           NoteTextField(value = viewModel.titleState, label = "Title", onValueChange = {viewModel.onTitleChange(it)})
           Spacer(modifier = Modifier.height(4.dp))
           NoteTextField(value = viewModel.descState, label = "Description", onValueChange = {viewModel.onDescChange(it)})
           Spacer(modifier = Modifier.height(4.dp))
           Button(
               onClick = {viewModel.updateNote(Note(id,viewModel.titleState,viewModel.descState))
                            scope.launch {
                              snackBarHostState.showSnackbar("Your Note Has Been Updated!!", duration = SnackbarDuration.Short)
                              navController.navigateUp()
                            }
               },
               colors = ButtonDefaults.buttonColors(colorResource(id = R.color.button))) {
               Text(text = "Update Note")
           }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
