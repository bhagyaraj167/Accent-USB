package com.bignerdranch.android.myapplication.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.bignerdranch.android.myapplication.model.Itunes
import com.bignerdranch.android.myapplication.model.Result
import com.bignerdranch.android.myapplication.navigation.Screen
import com.bignerdranch.android.myapplication.network.Status
import com.bignerdranch.android.myapplication.ui.theme.LIGHTBLUE
import com.bignerdranch.android.myapplication.viewmodel.ItunesViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun getItunes(itunesViewModel: ItunesViewModel = hiltViewModel(), navController: NavController) {

    //to maintain the ui state
    val showProgressbar = remember { mutableStateOf(true) }
    val showErrorDialog = remember { mutableStateOf(false) }
    val itunesData = remember { mutableStateOf(Itunes()) }

    Column {
        SearchComponent(itunesViewModel)
        itunesData.value.results?.let { getList(it, navController) }
    }
    LaunchedEffect(Unit) {
        launch {
            itunesViewModel.itunes.collect {
                when (it.status) {
                    Status.LOADING -> {
                        showProgressbar.value = true
                    }
                    Status.SUCCESS -> {
                        showProgressbar.value = false
                        itunesData.value = (it.data ?: Itunes())
                    }
                    Status.ERROR -> {
                        showErrorDialog.value = true
                    }
                }
            }
        }
    }
}

@Composable
fun getList(itunesList: List<Result>, navController: NavController) {
    LazyColumn {
        items(itunesList) { itunesItem ->
            //no long description
            ListItem(
                itunesItem,
                onItemClick = {
                    navController.navigate(Screen.ItunesDetailsScreen.route + "?trackName=${itunesItem.trackName}?artwork30=${itunesItem.artworkUrl30}?longDescription=${itunesItem.collectionCensoredName}")
                })
        }
    }
}

@Composable
fun SearchComponent(itunesViewModel: ItunesViewModel) {
    var text by rememberSaveable { mutableStateOf(("")) }
    Column() {
        Row(modifier = Modifier.fillMaxWidth()) {
            BasicTextField(
                value = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                onValueChange = {
                    text = it
                    itunesViewModel.getItunes(text, "musicVideo")
                },
                decorationBox = { innerTextField ->
                    Box(
                        Modifier
                            .background(Color.LightGray, RoundedCornerShape(percent = 20))
                            .padding(8.dp)
                    ) {
                        if (text.isEmpty()) {
                            Text("Search")
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}

@Composable
fun ListItem(
    item: Result,
    onItemClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(LIGHTBLUE)
            .clickable {
                onItemClick()
            },

        ) {
        Image(
            painter = rememberAsyncImagePainter(item.artworkUrl30),
            modifier = Modifier.size(100.dp),
            contentDescription = ""
        )
        Column {
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                text = item.trackName,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                text = item.trackPrice.toString(),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                text = item.kind,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

    }
    Spacer(modifier = Modifier.height(4.dp))
}