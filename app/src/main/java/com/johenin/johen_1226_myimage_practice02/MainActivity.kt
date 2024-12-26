package com.johenin.johen_1226_myimage_practice02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.johenin.johen_1226_myimage_practice02.ui.theme.Johen_1226_myimage_practice02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Johen_1226_myimage_practice02Theme {
                MainScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Johen_1226_myimage_practice02Theme {
        MainScreen()
    }
}

@Composable
fun MainScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        var imageUrl by remember { mutableStateOf<String>(" ") }
        var curFilter by remember { mutableStateOf(0) }
        when {
            curFilter == 0 -> OriginalImage(imageUrl)
            curFilter == 1 -> BWImage(imageUrl)
            curFilter == 2 -> ContrastImage(imageUrl)
            curFilter == 3 -> BlurredImage(imageUrl)
        }
        var text by remember { mutableStateOf("insert URL") }
        TextField(
            value = text,
            singleLine = true,
            onValueChange = { text = it },
            colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.Gray
            ),
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize()
        )
        Button(
            onClick = {
                imageUrl = text
                curFilter = 0
            },
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            border = BorderStroke(5.dp, color = Color.Magenta),
            modifier = Modifier
                .background(Color.Transparent)
        ) {
            Text(
                "Load image with URL",
                color = Color.Red,
                modifier = Modifier
                    .padding(10.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                curFilter = 0

            }) { Text("Original") }
            Button(onClick = {
                curFilter = 1
            }) { Text("B&W") }
            Button(onClick = {
                curFilter = 2
            }) { Text("Contrast") }
            Button(onClick = {
                curFilter = 3
            }) { Text("Blurred") }
        }
    }
}

@Composable
fun OriginalImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = "Translated description of what the image contains",
        modifier = Modifier
            .border(5.dp, Color.Green)
    )
}

@Composable
fun BWImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = "Translated description of what the image contains",
        modifier = Modifier
            .border(5.dp, Color.Green),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
    )
}

@Composable
fun ContrastImage(url: String) {
    val colorMatrix = floatArrayOf(
        -1f, 0f, 0f, 0f, 255f,
        0f, -1f, 0f, 0f, 255f,
        0f, 0f, -1f, 0f, 255f,
        0f, 0f, 0f, 1f, 0f
    )

    AsyncImage(
        model = url,
        contentDescription = "Translated description of what the image contains",
        modifier = Modifier
            .border(5.dp, Color.Green),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix))
    )
}

@Composable
fun BlurredImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = "Translated description of what the image contains",
        modifier = Modifier
            .border(5.dp, Color.Green)
            .blur(
                radiusX = 10.dp,
                radiusY = 10.dp,
                edgeTreatment = BlurredEdgeTreatment.Unbounded,
            )
    )
}