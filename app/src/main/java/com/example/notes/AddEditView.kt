package com.example.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notes.Data.Note
import kotlinx.coroutines.launch

@Composable
fun AddEditView(
                viewModel: NoteViewModel,
                navController: NavController){
    val snackBarState = remember {
        SnackbarHostState()
    }
    var snackMessage = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    viewModel.titleState = ""
    viewModel.descState = ""

    Scaffold(
        topBar = { TopBar(title = "Add Note",
        onNavClicked = {navController.navigateUp()})},
        snackbarHost = {SnackbarHost(snackBarState)},){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {
            NoteTextField(
                value = viewModel.titleState,
                label = "Title",
                onValueChange = {viewModel.onTitleChange(it)})
            
            Spacer(modifier = Modifier.height(4.dp))
            
            NoteTextField(value = viewModel.descState ,
                label = "Description",
                onValueChange = {viewModel.onDescChange(it)})

            Spacer(modifier = Modifier.height(4.dp))
            Button(onClick = {
                if(viewModel.titleState.isNotEmpty() && viewModel.descState.isNotEmpty()){
                    viewModel.addNote(Note(title = viewModel.titleState, desc = viewModel.descState))
                    snackMessage.value = "Note Added!!"
                    scope.launch {
                        snackBarState.showSnackbar(snackMessage.value)
                        navController.navigate(Screens.HomeScreen.route)
                    }
                }else{
                    snackMessage.value = "Please Fill The Entries"
                    scope.launch {
                        snackBarState.showSnackbar(snackMessage.value)
                    }
                }
            },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.button))) {
                Text(text = "Add Note")
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
