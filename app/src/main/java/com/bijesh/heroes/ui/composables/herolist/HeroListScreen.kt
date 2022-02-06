package com.bijesh.heroes.ui.composables.herolist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bijesh.heroes.model.HeroesResponse
import com.bijesh.heroes.ui.composables.HeroCard

@Composable
fun HeroListScreen(viewModel: HeroListViewModel?, universe: String?, cardClickCallback: (HeroesResponse) -> Unit) {
    viewModel!!.getHeroListFilteredByPublisher(universe)
    val heroes = viewModel.heroesState.value
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primaryVariant
    ) {
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(heroes) { hero ->
                HeroCard(hero = hero, cardClickCallback = cardClickCallback)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroListScreenPreview() {
    HeroListScreen(null, "marvel", {})
}