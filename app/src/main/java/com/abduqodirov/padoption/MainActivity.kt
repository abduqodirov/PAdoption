package com.abduqodirov.padoption

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
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
import com.abduqodirov.padoption.model.Dog
import com.abduqodirov.padoption.ui.theme.PAdoptionTheme
import com.abduqodirov.padoption.ui.theme.Shapes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dogs = listOf<Dog>(
            Dog("Heyy", 3, "Lablador"),
            Dog("Heyy", 3, "Lablador"),
            Dog("Heyy", 3, "Lablador"),
            Dog("Heyy", 3, "Lablador"),
        )

        setContent {
            PAdoptionTheme {

                DogsList(dogs = dogs)

            }
        }
    }

    @Composable
    fun DogsList(dogs: List<Dog>) {

        val listState = rememberLazyListState()

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            state = listState
        ) {

            items(items = dogs) { dog ->


                Surface(
                    color = Color.LightGray,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
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