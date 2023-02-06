package com.snappy.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snappy.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val firstArtwork = R.drawable.denji_face
        val secondArtwork = R.drawable.zero_two_face
        val thirdArtwork = R.drawable.sanji_face
        val fourthArtwork = R.drawable.naruto_face

        var title by remember {
            mutableStateOf( R.string.denji)
        }
        var year by remember {
            mutableStateOf(R.string.denji_year)
        }
        var currentArtwork by remember {
            mutableStateOf(firstArtwork)
        }

        ArtworkDisplay( currentArtwork = currentArtwork, modifier = Modifier.padding(10.dp))
        Spacer(modifier = Modifier.size(16.dp))
        ArtWorkTitle(title = title, year = year)
        Spacer(modifier = Modifier.size(25.dp))
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = {
                    when(currentArtwork){
                        firstArtwork -> {
                            currentArtwork = fourthArtwork
                            title = R.string.naruto
                            year = R.string.naruto_year
                        }
                        secondArtwork-> {
                            currentArtwork = firstArtwork
                            title = R.string.denji
                            year = R.string.denji_year
                        }
                        thirdArtwork ->{
                            currentArtwork = secondArtwork
                            title = R.string.zero_two
                            year = R.string.zero_two_year
                        }
                        else -> {
                            currentArtwork = thirdArtwork
                            title = R.string.sanji
                            year = R.string.sanji_year
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    Color.Blue
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 1.dp
                )
            ){
                Text(
                    text = "Previous",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
            Button(
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.zero_two
                            year = R.string.zero_two_year
                        }
                        secondArtwork -> {
                            currentArtwork = thirdArtwork
                            title = R.string.sanji
                            year = R.string.sanji_year
                        }
                        thirdArtwork -> {
                            currentArtwork = fourthArtwork
                            title = R.string.naruto
                            year = R.string.naruto_year
                        }
                        else -> {
                            currentArtwork = firstArtwork
                            title = R.string.denji
                            year = R.string.denji_year
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    Color.Blue
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ArtWorkTitle(@StringRes title: Int, @StringRes year: Int){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            fontSize = 32.sp
        )
        Text(
            text = stringResource(id = year),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
    }
}

@Composable
fun ArtworkDisplay(modifier: Modifier = Modifier, @DrawableRes currentArtwork: Int){
    Image(painter = painterResource(
        id = currentArtwork),
        contentDescription = stringResource(
            id = R.string.zero_two),
        modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyPreview(){
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}