package com.bijesh.heroes.ui.composables.search

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bijesh.heroes.model.HeroesResponse
import com.bijesh.heroes.ui.composables.HeroCard

@SuppressLint("RememberReturnType")
@Composable
fun SearchScreen(viewModel: SearchViewModel?, cardClickCallback: (HeroesResponse) -> Unit) {
    val heroes = viewModel!!.heroesState.value

    val favs = remember { mutableStateMapOf<Int, Boolean>() }
    remember(heroes) {
        if (favs.isNotEmpty()) {
            favs.clear()
            favs.putAll(heroes.associate { hero -> hero.id to hero.favorite })
        } else {
            favs.putAll(heroes.associate { hero -> hero.id to hero.favorite })
        }
    }

    fun favoriteClicked(index: Int, selected: Boolean, hero: HeroesResponse) {
        favs[hero.id] = selected
        viewModel.favoriteClicked(index, selected, hero)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primaryVariant
    ) {
        Column {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var textState by remember { mutableStateOf(TextFieldValue()) }
                OutlinedTextField(
                    value = textState,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .background(Color.White, CircleShape),
                    colors = TextFieldDefaults
                        .outlinedTextFieldColors(
                            textColor = Color.Black,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                    onValueChange = {
                        textState = it
                        viewModel.filterHeroesList(textState.text)
                    },
                    placeholder = { Text(text = "Find your hero") },
                    leadingIcon = { Icon(Icons.Filled.Search, "", tint = Color.Black) }
                )
            }
            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                itemsIndexed(heroes) { index, hero ->
                    HeroCard(
                        index,
                        hero = hero,
                        fav = favs[hero.id] ?: false,
                        ::favoriteClicked,
                        cardClickCallback = cardClickCallback
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(null, {})
}