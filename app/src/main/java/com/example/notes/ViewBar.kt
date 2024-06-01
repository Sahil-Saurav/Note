package com.example.notes

import androidx.compose.foundation.layout.heightIn
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
import kotlinx.coroutines.flow.Flow

@Composable
fun ViewBar(title: Flow<com.example.notes.Data.Note>, onNavClicked:()->Unit){

    TopAppBar(title = { Text(text = title.toString()) },
        elevation = 1.dp,
        modifier = Modifier.heightIn(max = 80.dp),
        navigationIcon = { IconButton(onClick = {onNavClicked()}) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack , contentDescription = null)
        }
                         },
        backgroundColor = colorResource(id = R.color.primary),
        contentColor = colorResource(id = R.color.white)
    )
}