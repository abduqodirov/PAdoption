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
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.abduqodirov.padoption.model.Dog
import com.abduqodirov.padoption.ui.theme.PAdoptionTheme
import org.intellij.lang.annotations.JdkConstants

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivityC"

    val dogs = listOf<Dog>(
        Dog(1, "Kattakon", 3, true, R.drawable.kattakon),
        Dog(2, "Blacky", 3, false, R.drawable.blacky),
        Dog(3, "Kiaren", 3, true, R.drawable.kiaren),
        Dog(4, "Yum", 3, false, R.drawable.yum),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            PAdoptionTheme {

                NavHost(navController = navController, startDestination = "overview") {
                    composable("overview") { OverviewScreen(navController) }
                    composable("details/{dogId}") { backStackEntry ->
                        DetailsScreen(navController, backStackEntry.arguments?.getString("dogId"))
                    }
                }
            }

        }

    }

    @Composable
    fun OverviewScreen(navController: NavController) {

        DogsList(
            dogs = dogs,
            onClick = {
                Log.d(TAG, "OverviewScreen: ${it}-element bosildi")
                navController.navigate("details/$it")
            }
        )

    }

    @Composable
    fun DetailsScreen(navController: NavController, dogId: String?) {

        if (dogId != null) {

            val id = dogId.toInt()

            val dog = dogs.find { dog -> dog.id == id }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight()
            ) {

                Image(
                    painter = painterResource(id = dog!!.imageId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)) {
                    DogInfo(info = "Nickname" to dog.name)

                    val dogGender = if (dog.isFemale) {
                        "Female"
                    } else {
                        "Male"
                    }

                    DogInfo(info = "Gender" to dogGender)

                    DogInfo(info = "Age" to dog.age.toString())
                }

            }

        }

    }

    @Composable
    fun DogInfo(info: Pair<String, String>) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "${info.first}: ",
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Normal)
            )
            Text(
                text = info.second,
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
            )
        }

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

                Card(
                    elevation = 4.dp,
                    modifier = Modifier
                        .padding(16.dp)
                        .height(256.dp)
                        .clickable { onClick(dog.id) }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Image(
                            painter = painterResource(id = dog.imageId),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.weight(1f)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {

                            Text(
                                text = dog.name,
                                modifier = Modifier.padding(8.dp),
                                style = MaterialTheme.typography.h4
                            )

                            val genderImageId = if (dog.isFemale) {
                                R.drawable.femenine
                            } else {
                                R.drawable.malegender
                            }

                            Row {

                                Icon(
                                    painter = painterResource(id = genderImageId),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )

                            }

                        }
                    }

                }
            }

        }
    }


}


