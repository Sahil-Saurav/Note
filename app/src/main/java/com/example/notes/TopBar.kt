package com.example.notes

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max


@Composable
fun TopBar(title:String,onNavClicked:()->Unit){
    val navigationIcon : (@Composable () -> Unit)?=
        if(!title.contains("My Notes")){
            {
                IconButton(onClick = {onNavClicked()}) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = colorResource(id = R.color.white))
                }
            }
        }else{
            null
        }
    TopAppBar(title = { Text(text = title)},
        elevation = 1.dp,
        modifier = Modifier.heightIn(max = 80.dp),
        navigationIcon = navigationIcon,
        backgroundColor = colorResource(id = R.color.primary),
        contentColor = colorResource(id = R.color.white))
}