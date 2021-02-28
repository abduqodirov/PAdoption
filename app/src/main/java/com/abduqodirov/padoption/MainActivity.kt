package com.abduqodirov.padoption

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.abduqodirov.padoption.model.Dog
import com.abduqodirov.padoption.ui.theme.PAdoptionTheme
import com.abduqodirov.padoption.ui.theme.Shapes

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivityC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "overview") {
                composable("overview") { OverviewScreen(navController) }
                composable("details") { DetailsScreen() }
            }

//            PAdoptionTheme {
//
//                OverviewScreen(navController)
//
//            }
        }

    }

    @Composable
    fun OverviewScreen(navController: NavController) {
        val dogs = listOf<Dog>(
            Dog(1, "Heyy", 3, "Lablador"),
            Dog(2, "Heyy", 3, "Lablador"),
            Dog(3, "Heyy", 3, "Lablador"),
            Dog(4, "Heyy", 3, "Lablador"),
        )

        DogsList(
            dogs = dogs,
            onClick = {
                Log.d(TAG, "OverviewScreen: ${it}-element bosildi")
                navController.navigate("details")
            }
        )

    }

    @Composable
    fun DetailsScreen() {
        Text(text = "this is details screen")
    }

    @Composable
    fun DogsList(dogs: List<Dog>, onClick: (Int) -> Unit) {

        val listState = rememberLazyListState()

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            state = listState
        ) {

            items(
                items = dogs,
                key = { dog ->
                    dog.id
                }
            ) { dog ->


                Surface(
                    color = Color.LightGray,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .clickable(onClick = { onClick(dog.id) })
                ) {
                    Row(Modifier.height(64.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.kattakon),
                            contentDescription = null
                        )
                        Text(
                            text = dog.name,
                            modifier = Modifier.padding(8.dp)
                        )

                    }
                }

            }

        }

    }

}