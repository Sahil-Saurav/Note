package com.example.notes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.Data.Note
import com.example.notes.Data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(val noteRepository: NoteRepository=Graph.noterepository):ViewModel() {
    var titleState by mutableStateOf("")
    var descState by mutableStateOf("")

    fun onTitleChange(newString:String){
        titleState= newString
    }
    fun onDescChange(newString:String){
        descState = newString
    }

    lateinit var getAllNote: Flow<List<Note>>

    init {
        getAllNote = noteRepository.getAllNote()
    }

    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.addNote(note)
        }
    }
    fun deleteNote(note: Note){
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }
    fun updateNote(note: Note){
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }
    fun getNoteById(id:Long):Flow<Note>{
        return noteRepository.getNoteById(id)
    }
}