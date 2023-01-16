package com.snappy.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snappy.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard(){
    Row(
        Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.backgroundColor)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            val image = painterResource(R.drawable.ic_android_black_24dp)
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
            Text(
                text = "Aman Kumar"
                , fontSize = 60.sp,
                color = Color.White
            )
            Text(
                text = "Android Developer Extraordinaire",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(
                id = R.color.green
            ))
        }
    }
    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom, modifier = Modifier.padding(bottom = 40.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val callImg = painterResource(R.drawable.baseline_call_24)
                Image(
                    painter = callImg,
                    contentDescription = null
                )
                Text(
                    text = "9876543210",
                    Modifier.padding(10.dp),
                    fontSize = 20.sp, color =
                    Color.White)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val callImg = painterResource(R.drawable.baseline_share_24)
                Image(
                    painter = callImg,
                    contentDescription = null
                )
                Text(
                    text = "@AmanDahiya",
                    Modifier.padding(10.dp),
                    fontSize = 20.sp,
                    color = Color.White)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val callImg = painterResource(R.drawable.baseline_email_24)
                Image(
                    painter = callImg,
                    contentDescription = null
                )
                Text(
                    text = "iAmAman@gmail.com",
                    Modifier.padding(10.dp),
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
        
    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyPreview(){
    BusinessCardTheme {
        BusinessCard()
    }
}