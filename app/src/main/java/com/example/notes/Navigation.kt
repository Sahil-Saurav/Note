package com.example.notes

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(viewModel: NoteViewModel=viewModel(),navController:NavHostController= rememberNavController()){
        NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
            composable(route = Screens.HomeScreen.route){
                HomeView(viewModel,navController)
            }
            composable(route = Screens.AddEditScreen.route) {
                AddEditView(viewModel = viewModel, navController = navController)
            }
            composable(route=Screens.ViewScreen.route+"/{title}/{desc}"){
                val title = it.arguments?.getString("title")?:"no title"
                val desc = it.arguments?.getString("desc")?:"no desc"
                ViewScreen(title = title, desc = desc, viewModel = viewModel, navController = navController)
            }
            composable(route= Screens.UpdateScreen.route+"/{id}/{title}/{desc}"){
                val id = it.arguments?.getString("id")?: "0L"
                val title = it.arguments?.getString("title")?: "no title"
                val desc = it.arguments?.getString("desc")?: "no desc"
                UpdateScreen(id,title,desc,viewModel,navController)
            }
        }
}