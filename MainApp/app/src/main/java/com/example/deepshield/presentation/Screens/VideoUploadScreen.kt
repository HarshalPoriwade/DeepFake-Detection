package com.example.deepshield.presentation.Screens

import android.net.Uri
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.deepshield.R
import com.example.deepshield.presentation.Navigation.VIDEOPROCESSINGSCREEN
import com.example.deepshield.presentation.viewModel.MyViewModel
import com.shashank.sony.fancytoastlib.FancyToast

@Composable
fun VideoUploadingScreenBin(viewModel: MyViewModel = hiltViewModel(), videoUri: String,imageUri: String,navController: NavController) {
    val context = LocalContext.current
    val lottiecomposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.button))
    val progress2 by animateLottieCompositionAsState(
        composition = lottiecomposition,
        iterations = LottieConstants.IterateForever,
        speed = 0.75f
    )//for button
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(videoUri)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = false// Auto-play
        }
    }
    Spacer(modifier = Modifier.height(32.dp))
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = true // Show play/pause controls
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f).height(450.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))

        Box(
            contentAlignment = Alignment.Center,  // Centers the text inside the animation
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(50.dp)
                .clickable {
                    FancyToast.makeText(context,"Video Upload",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show()
                    navController.navigate(VIDEOPROCESSINGSCREEN(imageUri = imageUri, videoUri = videoUri))
                }
        ) {
            // Lottie Animation
            LottieAnimation(
                composition = lottiecomposition,
                progress = { progress2 },
                modifier = Modifier.fillMaxWidth()  // Makes animation fill the Box
            )

            // Overlayed Text
            Text(
                text = "Upload Video",  // Your desired text
                color = Color.White,  // Adjust color for visibility
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        DisposableEffect(Unit) {
            onDispose {
                exoPlayer.release() // Release player when composable is removed
            }
        }

    }
}