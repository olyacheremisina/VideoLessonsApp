package com.example.videolessons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.videolessons.ui.theme.ColorOne
import com.example.videolessons.ui.theme.ColorThree
import com.example.videolessons.ui.theme.ColorTwo
import com.example.videolessons.ui.theme.MainColor
import com.example.videolessons.ui.theme.VideoLessonsTheme

var text = "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, ipsum dolor sit amet\""

var listOfUsers = listOf<User>(
    User(R.drawable.image2, "Alexey", "Volkov", text),
    User(R.drawable.image3, "Vladimir", "Ivanov", text),
    User(R.drawable.image4, "Alexandr", "Abramov", text),
    User(R.drawable.image1, "Anastasia", "Belyaeva", text),
    User(R.drawable.image6, "Viktor", "Borodin", text),
    User(R.drawable.image7, "Oleg", "Trubin", text),
    User(R.drawable.image5, "Olga", "Ivanova", text),
    User(R.drawable.image8, "Alexey", "Volkov", text),
    User(R.drawable.image3, "Vladimir", "Ivanov", text),
    User(R.drawable.image4, "Alexandr", "Abramov", text),
    User(R.drawable.image1, "Anastasia", "Belyaeva", text),
    User(R.drawable.image7, "Oleg", "Trubin", text),
    User(R.drawable.image5, "Olga", "Ivanova", text),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
             Column(modifier = Modifier.fillMaxSize()){
                 Box(modifier = Modifier
                     .fillMaxWidth()
                     .background(MainColor)) {
                     Column(modifier = Modifier.padding(0.dp, 10.dp),
                         horizontalAlignment = Alignment.CenterHorizontally) {
                         Text(text = "Карточки в строку:", fontSize = 18.sp)
                         LazyRow(){
                             items(listOfUsers){ item ->
                                 UserCard(item,"Some text")
                             }
                         }
                     }
                 }

                 Box(modifier = Modifier
                     .fillMaxWidth()
                     .background(MainColor)) {
                     Column(modifier = Modifier.padding(0.dp, 10.dp),
                         horizontalAlignment = Alignment.CenterHorizontally) {
                         Text(text = "Карточки в колонку:", fontSize = 18.sp)
                         LazyColumn(){
                             items(listOfUsers){ item ->
                                 UserCard(item, item.Content)
                             }
                         }
                     }
                 }
             }
        }
    }
}

@Composable
fun UserCard(item: User, content: String){
    var counter = remember {
        mutableStateOf(0)
    }

    var colorState = remember {
        mutableStateOf(Color.White)
    }

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .background(colorState.value)
        .clickable {
            counter.value++
            when (counter.value) {
                3 -> colorState.value = ColorOne
                5 -> colorState.value = ColorTwo
                10 -> colorState.value = ColorThree
            }
        },
        colors = CardDefaults.cardColors(
            containerColor = colorState.value
        ))
    {
        Row(modifier = Modifier
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = item.ImageId),
                contentDescription = "User image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(3.dp)
                    .size(64.dp)
                    .clip(CircleShape))
            Column {
                Row {
                    Text(modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                        text = item.UserName)
                    Text(modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                        text = item.UserSurname)
                    Text(modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                        text = "Счетчик: ${counter.value}")
                }
                Row {
                    Text(modifier = Modifier
                        .padding(10.dp, 10.dp, 0.dp, 0.dp)
                        .clickable {
                            isExpanded = !isExpanded
                        }, maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        text = content)
                }
            }
        }
    }
}