package com.bignerdranch.android.myapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ITunesDetailsScreen(
    trackName: String?,
    artImage: String?=null,
    longDesc: String?=null
){
    Column() {
        trackName?.let { Text(text = it, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )) }
       artImage?.let {
           Image(
               painter = rememberAsyncImagePainter(artImage),
               modifier = Modifier.size(100.dp),
               contentDescription = "")
       }

        trackName?.let { longDesc?.let { it1 -> Text(text = it1, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )) } }
    }
}