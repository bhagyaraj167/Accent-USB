package com.bignerdranch.android.myapplication.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun dropdDownMenu(entity: MutableState<String>) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val entities = listOf("song", "musicArtist","musicVideo","musicTrack","podcast","tvShow")
    var selectedText by remember {
        mutableStateOf("")
    }
    entity.value = selectedText
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            label = { Text("Label") },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            })
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            entities.forEach { entity ->
                DropdownMenuItem(onClick = {
                    selectedText = entity
                    expanded = false
                }) {
                    Text(text = entity)
                }

            }
        }
    }
}