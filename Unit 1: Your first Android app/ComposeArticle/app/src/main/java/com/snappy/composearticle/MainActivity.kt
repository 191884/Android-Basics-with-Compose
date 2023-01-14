package com.snappy.composearticle

import android.graphics.Paint.Style
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snappy.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArticleTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ComposeArticle()
                }
            }
        }
    }
}

@Composable
fun ComposeArticle(){
    val image = painterResource(R.drawable.bg_compose_background)
    Column{
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()
                .wrapContentWidth(Alignment.Start),
        )
        Text(
            text = stringResource(R.string.Title),
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(16.dp)
        )
        
        Text(
            text = stringResource(R.string.ShortDescription),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        )
        
        Text(
            text = stringResource(R.string.LongDescription),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun myPreview(){
    ComposeArticleTheme {
        ComposeArticle()
    }
}
