package com.example.notes

import android.content.Context
import androidx.room.Room
import com.example.notes.Data.NoteDataBase
import com.example.notes.Data.NoteRepository

object Graph {
    lateinit var database : NoteDataBase

    val noterepository by lazy {
        NoteRepository(database.noteDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context,NoteDataBase::class.java,"note.db").build()
    }
}