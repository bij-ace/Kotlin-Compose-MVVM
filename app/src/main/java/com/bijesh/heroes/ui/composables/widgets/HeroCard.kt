package com.bijesh.heroes.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.bijesh.heroes.R
import com.bijesh.heroes.model.HeroesResponse

@Composable
fun HeroCard(
    index: Int,
    hero: HeroesResponse,
    fav: Boolean,
    favoriteClickCallback: (Int, Boolean, HeroesResponse) -> Unit,
    cardClickCallback: (HeroesResponse) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    var bioModifier = if (isExpanded)
        Modifier
            .fillMaxWidth(0.8f)
            .wrapContentHeight() else
        Modifier
            .fillMaxWidth(0.8f)
            .height(150.dp)

    fun favClicked(selected: Boolean) {
        favoriteClickCallback(index, selected, hero)
    }

    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { cardClickCallback(hero) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .animateContentSize()
        ) {
            Column {
                Image(
                    painter = rememberImagePainter(
                        data = hero.images?.md,
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(88.dp)
                        .padding(top = 5.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Image(
                    painter = painterResource(id = if (fav) R.drawable.favorite_selected else R.drawable.favorite_unselected),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(40.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable { favClicked(!fav) }
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = bioModifier
            ) {
                Text(
                    text = "${hero.name}",
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = "Full name: ${hero.biography?.fullName}",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = "Aliases: ${hero.biography?.aliases}",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = "Place of birth: ${hero.biography?.placeOfBirth}",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = "Alter egos: ${hero.biography?.alterEgos}",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = "Alignment: ${hero.biography?.alignment}",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = "Publisher: ${hero.biography?.publisher}",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = "First appearance: ${hero.biography?.firstAppearance}",
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Icon(
                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand row icon",
                modifier = Modifier
                    .align(if (isExpanded) Alignment.Bottom else Alignment.CenterVertically)
                    .padding(10.dp)
                    .clickable { isExpanded = !isExpanded }
            )
        }
    }
}