package com.snappy.gridapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snappy.gridapp.data.DataSource
import com.snappy.gridapp.model.Topic
import com.snappy.gridapp.ui.theme.GridAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GridApp()
                }
            }
        }
    }
}

@Composable
fun GridApp(){
    GridAppTheme() {
        TopicList()
    }
}

@Composable
fun TopicList(){
    val topicList = DataSource().loadData()
    GridList(topicList)
}

@Composable
fun GridList(topicList: List<Topic>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(8.dp)
    ){
        items(topicList) {
            TopicCard(it)
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier){
    Card(elevation = 4.dp) {
        Row() {
            Box(){
                Image(
                    painter = painterResource(topic.pic),
                    contentDescription = stringResource(id = topic.name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 68.dp, height = 68.dp)
                        .aspectRatio(1f)
                )
            }
            Column() {
                Text(text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
                Row {
                    Icon(painter = painterResource(id = R.drawable.grain), contentDescription = null,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(12.dp)
                    )
                    Text(text = topic.num.toString(), style = MaterialTheme.typography.caption, modifier= modifier.padding(8.dp))
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TopicPreview(){
//    GridAppTheme {
//        val topic = Topic(R.string.photography, 321, R.drawable.photography)
//        Column(modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            TopicCard(topic = topic)
//        }
//    }
    GridApp()
}