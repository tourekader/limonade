package com.example.limonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.limonade.ui.theme.LimonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LimonadeTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    Lemonade()
                }
            }
        }
    }
}

@Composable
fun Lemonade() {
    val image = arrayOf(
        painterResource(R.drawable.lemon_tree),
        painterResource(R.drawable.lemon_squeeze),
        painterResource(R.drawable.lemon_drink),
        painterResource(R.drawable.lemon_restart)
    )
    val text = arrayOf(
        stringResource(R.string.lemonade_tree),
        stringResource(R.string.lemonade),
        stringResource(R.string.jus_citron),
        stringResource(R.string.ver_vide)
    )

    val currentIndex = remember { mutableStateOf(0) }
    val squeezeClickCount = remember { mutableStateOf(0) }

    Button(
        onClick = {
            if (currentIndex.value == 1) {
                squeezeClickCount.value += 1
            }
            if (currentIndex.value == 1 && squeezeClickCount.value >= 3) {
                squeezeClickCount.value = 0
                currentIndex.value = (currentIndex.value + 1) % image.size
            } else if (currentIndex.value != 1) {
                currentIndex.value = (currentIndex.value + 1) % image.size
            }
        }, colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = Modifier.padding(16.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = image[currentIndex.value], contentDescription = null)
            Text(text = text[currentIndex.value], color = colorResource(id = R.color.black))

            Spacer(modifier = Modifier.height(16.dp))
            if (currentIndex.value == 1 && squeezeClickCount.value < 3) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LimonadeTheme {
        Limonade()
    }
}