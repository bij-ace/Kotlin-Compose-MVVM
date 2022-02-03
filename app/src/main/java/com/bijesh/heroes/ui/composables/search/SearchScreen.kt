package com.bijesh.heroes.ui.composables.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bijesh.heroes.model.HeroesResponse
import com.bijesh.heroes.ui.composables.HeroCard
import com.bijesh.heroes.ui.composables.herolist.HeroListViewModel

@Composable
fun SearchScreen(cardClickCallback: (HeroesResponse) -> Unit) {
    val viewModel: HeroListViewModel = viewModel()
    val heroes = viewModel.heroesState.value

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
                items(heroes) { hero ->
                    HeroCard(hero = hero, cardClickCallback = cardClickCallback)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen({})
}