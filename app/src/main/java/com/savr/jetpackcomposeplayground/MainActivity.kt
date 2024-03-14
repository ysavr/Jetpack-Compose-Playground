package com.savr.jetpackcomposeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.savr.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    var position by remember { mutableIntStateOf(1) }

    val images = painterResource(id = getArtImage(position = position))
    val tittle = getArtTittle(position = position)
    val subTittle = getArtSubTittle(position = position)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
    ) {
        Card(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth()
                .padding(all = 12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = images,
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .aspectRatio(16f / 9f)
                        .padding(top = 12.dp, bottom = 12.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = tittle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 25.dp, end = 25.dp)
            )
            Text(
                text = subTittle,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 25.dp, end = 25.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(onClick = {
                    if (position > 1) {
                        position -= 1
                    } else {
                        position = 1
                    }
                }) {
                    Text("Previous")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (position < 3) {
                        position += 1
                    } else {
                        position = 3
                    }
                }) {
                    Text("Next")
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

fun getArtImage(position: Int): Int {
    return when (position) {
        1 -> R.drawable.van_gogh
        2 -> R.drawable.smitty
        else -> R.drawable.da_vinci
    }
}

fun getArtTittle(position: Int): String {
    return when (position) {
        1 -> "Vincent Van Gogh"
        2 -> "Smitty Werbenjagermanjensen"
        else -> "Leonardo Da Vinci"
    }
}

fun getArtSubTittle(position: Int): String {
    return when (position) {
        1 -> "Chalk Zone"
        2 -> "Spongebob SquarePants "
        else -> "Da Vinci Code"
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    JetpackComposePlaygroundTheme {
        ArtSpaceLayout()
    }
}