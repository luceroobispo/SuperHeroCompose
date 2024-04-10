package com.hero.superherocompose.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hero.superherocompose.R
import com.hero.superherocompose.model.data.Biography
import com.hero.superherocompose.model.data.Hero
import com.hero.superherocompose.model.data.HeroWrapper
import com.hero.superherocompose.model.data.Image

val heroData = HeroWrapper(
        heroes = listOf(
            Hero(
                name = "Peter Parker",
                biography = Biography(fullName = "Tierra - 616"),
                image = Image(url = "https://cdn.alfabetajuega.com/alfabetajuega/2022/12/spiderman-original.jpg?width=1200"),
                isFavorite = true
            ),
            Hero(
                name = "Spider-Gwen",
                biography = Biography(fullName = "Tierra - 65"),
                image = Image(url = "https://cdn.alfabetajuega.com/alfabetajuega/2022/12/spider-gwen.1690563431.1645.jpg?width=1200"),
                isFavorite = false
            ),
            Hero(
                name = "Miles Morales",
                biography = Biography(fullName = "Tierra - 1610"),
                image = Image(url = "https://cdn.alfabetajuega.com/alfabetajuega/2022/12/miles-morales.1690563418.7074.jpg?width=1200"),
                isFavorite = false
            )
        )
    )


@Composable
fun Home() {

    val heroes = remember {
        mutableStateOf(heroData.heroes)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Fondo negro para toda la pantalla
    ) {
        Scaffold { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                //HeroSearch(name, heroes)
                HeroList(heroes = heroes)
            }
        }
    }
}

/*
@Composable
fun HeroSearch() {

}
*/

@Composable
fun HeroList(heroes: MutableState<List<Hero>>) {

    LazyColumn {
        items(heroes.value) { hero ->
            HeroCard(hero = hero)
        }
    }
}

@SuppressLint("ResourceAsColor")
@Composable
fun HeroCard(hero: Hero) {
    Card(modifier = Modifier.fillMaxWidth().
        padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Gray)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = hero.image.url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(92.dp)
            )
            Column(modifier = Modifier.weight(1f).padding(10.dp)) {
                Text(text = hero.name,
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )
                Text(text = hero.biography.fullName,
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp))
            }
            FavoriteIcon(isFavorite = hero.isFavorite)
        }
    }
}

@Composable
fun FavoriteIcon(isFavorite: Boolean, modifier: Modifier = Modifier) {
    val iconResource = if (isFavorite) R.drawable.heart_complete else R.drawable.heart_default

    val icon: Painter = painterResource(id = iconResource)

    Icon(
        painter = icon,
        contentDescription = "Favorite",
        modifier = modifier.size(40.dp).padding(end=20.dp)
    )
}
