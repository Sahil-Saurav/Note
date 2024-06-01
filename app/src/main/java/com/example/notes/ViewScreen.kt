package com.example.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun ViewScreen(title:String,desc:String,viewModel: NoteViewModel,navController: NavController){
    Scaffold(
        topBar = { TopBar(title = "Title: "+title,
            onNavClicked = {navController.navigateUp()})}) {
        Column (modifier = Modifier
            .padding(it)
            .background(colorResource(id = R.color.background))
            .fillMaxSize()){
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Description:",
                fontSize = 30.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 25.dp),
                color = colorResource(id = R.color.head_font)
            )
            Text(
                text = desc,
                fontSize = 30.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(25.dp)
                    .fillMaxSize()
                    .background(colorResource(id = R.color.background))
                    .verticalScroll(rememberScrollState())
            )
        }
    }
}
