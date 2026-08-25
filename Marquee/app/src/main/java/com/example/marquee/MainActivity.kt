package com.example.marquee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.InspectableProperty
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marquee.ui.theme.MarqueeTheme
import kotlinx.coroutines.internal.MainDispatcherFactory
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {
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


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Greeting() {
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
        var text by remember { mutableStateOf("") }
        var sliderValue by remember { mutableStateOf(0f) }
        var simpleFormat = SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
        var time = simpleFormat.format(Date())
        var output by remember{ mutableStateOf("${time}")}

        Column (modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ){
            TextField(
                value = text,
                onValueChange = {newText ->text = newText},
                placeholder = { Text("請輸入顯示的文字")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Slider(
                value = sliderValue,
                valueRange = 0f..100f,
                onValueChange = {
                    sliderValue=it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Button(
                onClick = {
                    output = text

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ){
                Text("更新文字", fontSize = 20.sp)
            }
            Text(
                "${output}",
                fontSize = 20.sp,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .basicMarquee(
                        iterations = Int.MAX_VALUE,
                        animationMode = MarqueeAnimationMode.Immediately,
                        velocity = sliderValue.dp
                    )
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarqueeTheme {
        Greeting()
    }
}