package com.example.lottieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.person))
            var isPlaying by remember { mutableStateOf(true) }

            val progress by animateLottieCompositionAsState(
                composition = composition,
                isPlaying = isPlaying,
                iterations = 2
            )

            LaunchedEffect(key1 = progress) {
                if (progress == 0f) {
                    isPlaying = true
                }
                if (progress == 1f) {
                    isPlaying = false
                }
            }
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    LottieAnimation(
                        composition = composition,
                        modifier = Modifier
                            .size(300.dp),
                        progress = {
                            if (progress ==1f){ isPlaying = true }
                            progress
                        }
                    )
                    Button(onClick = { isPlaying = true}) {
                        Text(text = "Play again")
                    }
                }
            }
        }
    }
}
