package com.example.notes.Data

import kotlinx.coroutines.flow.Flow

class NoteRepository(val noteDao: NoteDao) {
    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }
    fun getAllNote():Flow<List<Note>>{
        return noteDao.getAllNote()
    }
    fun getNoteById(id:Long):Flow<Note>{
        return noteDao.getNoteById(id)
    }
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }

}