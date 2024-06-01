package com.example.notes

sealed class Screens(val route:String) {
    object HomeScreen:Screens("home_screen")
    object AddEditScreen:Screens("add_edit_screen")
    object ViewScreen:Screens("view_screen")
    object UpdateScreen:Screens("update_screen")
}