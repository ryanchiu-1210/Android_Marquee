package com.example.marquee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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


@OptIn(ExperimentalMaterial3Api::class)
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
                valueRange = 0f..10f,
                onValueChange = {
                    sliderValue=it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ){
                Text("更新文字", fontSize = 20.sp)
            }

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