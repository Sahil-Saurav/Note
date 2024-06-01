package com.example.notes

import android.app.Application

class NoteApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}