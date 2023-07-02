package com.womceo.breeds.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.womceo.breeds.data.BreedsRepository
import com.womceo.breeds.data.remote.BreedRemoteImpl
import com.womceo.breeds.data.remote.retrofit.BreedsWebService
import com.womceo.breeds.presentation.list.ListViewModel
import com.womceo.breeds.presentation.list.mapper.ListMapper
import com.womceo.breeds.ui.list.ListScreen
import com.womceo.breeds.ui.list.components.ListButton
import com.womceo.breeds.ui.navigation.NavGraph
import com.womceo.breeds.ui.theme.WomCeoTheme
import com.womceo.network.retrofit.WebServiceFactory

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WomCeoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavGraph(activity = this)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
