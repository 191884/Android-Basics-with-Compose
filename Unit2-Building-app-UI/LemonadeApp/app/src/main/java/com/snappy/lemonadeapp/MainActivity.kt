package com.snappy.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snappy.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                LamonadeApp()
            }
        }
    }
}
@Composable
fun LamonadeApp(){
    var currentStep by remember { mutableStateOf(1)}
    var squeezeCount by remember { mutableStateOf(0)}

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background )
    {
        when(currentStep){
            1 -> {
                LamonadeWithImageAndText(
                    textLabelResourceId = R.string.lemon_select,
                    drawableResourceId = R.drawable.lemon_tree,
                    ContentDescription = R.string.lemon_tree_content_description,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }
                )
            }
            2 -> {
                LamonadeWithImageAndText(
                    textLabelResourceId = R.string.lemon_squeeze,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    ContentDescription = R.string.lemon_content_description,
                    onImageClick = {
                        squeezeCount--
                        if(squeezeCount == 0){
                            currentStep = 3
                        }
                    })
            }
            3 -> {
                LamonadeWithImageAndText(
                    textLabelResourceId = R.string.lemon_drink,
                    drawableResourceId = R.drawable.lemon_drink,
                    ContentDescription = R.string.lemon_drink,
                    onImageClick = { currentStep = 4 })
            }
            4 -> {
                LamonadeWithImageAndText(
                    textLabelResourceId = R.string.lemon_empty_glass,
                    drawableResourceId = R.drawable.lemon_restart,
                    ContentDescription = R.string.empty_glass_content_description,
                    onImageClick = { currentStep = 1 })
            }
        }
    }
}

@Composable
fun LamonadeWithImageAndText(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    ContentDescription: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = textLabelResourceId), fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        Image(painter = painterResource(id = drawableResourceId),
            contentDescription = stringResource(id = ContentDescription),
            modifier
                .wrapContentSize()
                .clickable(onClick = onImageClick)
                .padding(16.dp)
                .border(2.dp, Color.Blue)
        )
        
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview(){
    LemonadeAppTheme {
        LamonadeApp()
    }
}