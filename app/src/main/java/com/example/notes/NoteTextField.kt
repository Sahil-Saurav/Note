package com.example.notes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun NoteTextField(value:String,label:String,onValueChange:(String)->Unit){
    OutlinedTextField(
        value = value,
        onValueChange=onValueChange,
        label = { Text(text = label, color = colorResource(id = R.color.button))},
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.black),
            unfocusedTextColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.primary),
            unfocusedLabelColor = colorResource(id = R.color.primary),
            cursorColor =colorResource(id = R.color.primary),
            unfocusedBorderColor = colorResource(id = R.color.primary),
            focusedBorderColor = colorResource(id = R.color.primary),
        )
    )
}
