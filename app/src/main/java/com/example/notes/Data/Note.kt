package com.example.notes.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note-table")

data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0L,
    @ColumnInfo("title")
    val title:String="",
    @ColumnInfo("desc")
    val desc:String=""){

}

object DummyData{
    val note = listOf(
        Note(0L,"Custom","sdghfuhvgbfhngvnsuygdfuhsgbuchnf"),
        Note(1L,"Custom 1","ihagdghagduygasyudguyasgdygasuygdusyagduysgadyu"),
        Note(2L,"Custom 2","uihdfuiashuidhuiasduihaiudhuiahduihasuidhuiashduiaghduihasuidgigfyweahfiuyguyf"))
}