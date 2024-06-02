package com.birukb.biruksart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.birukb.biruksart.ui.theme.BiruksArtTheme

// Data class for storing artwork details
data class Artwork(
    val title: String,
    val artist: String,
    val year: String,
    val imageResId: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BiruksArtTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtNavigation()
                }
            }
        }
    }
}


@Composable
fun ArtNavigation() {
    var currentArtIndex by remember { mutableIntStateOf(0) }
    val artworks = listOf(
        Artwork("The Tree Ghost", "Biruk.B", "2024", R.drawable.art1),
        Artwork("Addiction", "Biruk.B", "2024", R.drawable.art2),
        Artwork("The Broken Road", "Biruk.B", "2024", R.drawable.art3),
        Artwork("Me Time In The Woods", "Biruk.B", "2024", R.drawable.art4),
        Artwork("The Ghost and The Horn", "Biruk.B", "2024", R.drawable.art5),
        Artwork("The Dragon Ghost", "Biruk.B", "2024", R.drawable.art6)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Biruk's Art Gallery",
            style = MaterialTheme.typography.headlineMedium.copy(color = Color(0xFF009688)),

            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.weight(1f)) {
            AnimatedContent(
                targetState = artworks[currentArtIndex],
                transitionSpec = {
                    fadeIn(animationSpec = tween(1000)) togetherWith fadeOut(animationSpec = tween(1000))
                }, label = ""
            ) { artwork ->
                ArtworkView(artwork = artwork)
            }
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (currentArtIndex > 0) currentArtIndex -= 1
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                Text("Previous")
            }

            Spacer(Modifier.width(8.dp))

            Button(
                onClick = {
                    if (currentArtIndex < artworks.size - 1) currentArtIndex += 1
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                Text("Next")
            }
        }
    }
}

@Composable
fun ArtworkView(artwork: Artwork) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(12.dp, Color.Gray)  // Outer thick frame
                .padding(4.dp)
                .background(Color.Black)  // Black background to fill the white space
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .border(8.dp, Color.DarkGray)  // Inner thick frame
                    .padding(4.dp)
            ) {
                Image(
                    painter = painterResource(id = artwork.imageResId),
                    contentDescription = "${artwork.title} by ${artwork.artist}, ${artwork.year}",
                    contentScale = ContentScale.Fit,  // image fits within the frame without being cut off
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Spacer(Modifier.height(16.dp))  // space between image and info box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)  // Fixed height for the info box
                .background(Color.LightGray)  // Light gray background for the info box
                .padding(8.dp)  // Padding inside the info box
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = artwork.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "by ${artwork.artist} (${artwork.year})",
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BiruksArtTheme {
        ArtNavigation()
    }
}
