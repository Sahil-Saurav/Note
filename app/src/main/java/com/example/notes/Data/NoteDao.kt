package com.example.notes.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
abstract class NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend abstract fun addNote(noteEntity:Note)

    @Query("Select * from `note-table`")
    abstract fun getAllNote():Flow<List<Note>>

    @Query("Select * from `note-table` where id=:id")
    abstract fun getNoteById(id:Long):Flow<Note>

    @Update
    suspend abstract fun updateNote(noteEntity: Note)

    @Delete
    suspend abstract fun deleteNote(noteEntity: Note)


}