package com.hero.superherocompose.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hero.superherocompose.model.data.Hero
import com.hero.superherocompose.repositories.HeroRepository
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun Home() {

    val heroes = remember{
        mutableStateOf(emptyList<Hero>())
    }

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
           //HeroSearch(heroes)
           HeroList(heroes)
        }
    }
}

@Composable
fun HeroList(heroes: MutableState<List<Hero>>) {

    HeroRepository().getHeroes {
        heroes.value = it
    }

    LazyColumn {
        items(heroes.value) { hero ->
            HeroCard(hero = hero)
        }
    }
}


@Composable
fun HeroCard(hero: Hero) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Gray)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            HeroImage(hero.image.url)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(text= hero.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text= hero.biography.fullName, fontSize = 16.sp)
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favorite"
                )
            }
        }
    }
}


@Composable
fun HeroImage(url: String) {
    GlideImage(
        imageModel = { url },
        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
        modifier = Modifier.size(92.dp)
    )
}

/*
@Composable
fun HeroSearch() {

    val search remember{
        mutableStateOf("")
    }
    OutlinedTextField(
        value = search.value,
        onValueChange = { search.value = it },
        placeholder = { Text("Search hero") },
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search")
        },
        keyboardOptions=KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
*/

