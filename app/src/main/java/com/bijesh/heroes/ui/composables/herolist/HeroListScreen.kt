package com.bijesh.heroes.ui.composables.herolist

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bijesh.heroes.model.HeroesResponse
import com.bijesh.heroes.ui.composables.HeroCard

@SuppressLint("RememberReturnType")
@Composable
fun HeroListScreen(viewModel: HeroListViewModel?, universe: String?, cardClickCallback: (HeroesResponse) -> Unit) {
    val heroes = viewModel!!.heroesState.value
    viewModel!!.getHeroListFilteredByPublisher(universe)

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

@Preview(showBackground = true)
@Composable
fun HeroListScreenPreview() {
    HeroListScreen(null, "marvel", {})
}