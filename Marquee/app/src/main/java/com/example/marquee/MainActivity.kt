package com.example.marquee

import android.os.Build
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.DefaultTab.AlbumsTab.value
import androidx.annotation.InspectableProperty
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Label
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentCompositionErrors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marquee.ui.theme.MarqueeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.internal.MainDispatcherFactory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarqueeTheme {
                Greeting()
                }
            }
        }
    }


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Greeting() {
    var text by rememberSaveable { mutableStateOf("") }
    var sliderValue by rememberSaveable { mutableStateOf(200f) }
    var offsetss by rememberSaveable { mutableStateOf(MaxOffset(80f)) }
    var speed by rememberSaveable { mutableStateOf(1f)  }
    var simpleFormat = SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
    var time = simpleFormat.format(Date())

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("淘汰賽第一題", fontSize = 25.sp)
                }
            )
        }
    ){innerPadding->

        LaunchedEffect(Unit) {

            while (true){
                var simpleFormat1 = SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
                var time1 = simpleFormat.format(Date())
                time = time1
                delay(10)
                if(offsetss>900){
                    offsetss=-200f
                    offsetss+= 5f
                    delay(10)
                }
                else{
                    offsetss+= speed
                    delay(10)
                }

            }
        }

            TextField(
                value = text,
                singleLine = true,
                onValueChange = { newtext->text=newtext},
                placeholder = { Text("輸入跑馬燈文字，然後按下enter鍵確認…")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .padding(innerPadding)
            )
            Slider(
                value = sliderValue,
                valueRange = 50f..1000f,
                onValueChange = {
                    sliderValue=it
                    speed = sliderValue/200f
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(70.dp)
                    .padding(innerPadding),
                steps = 18
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center
            ){
                Text(

                    if(text.isNullOrBlank()) time else text,
                    fontSize = 20.sp,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .offset(
                            x = offsetss.dp,
                            //y = 210.dp
                        ),

                    )
            }

        }
    }




fun GetCurrentOffset(current:Float,add:Float):Float{
    return current+add
}

fun MaxOffset(current:Float):Float{
    if(current>1000f){
        return -50f
    }else{
        return current
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarqueeTheme {
        Greeting()
    }
}