package com.bijesh.heroes.ui.composables.herodetails

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter

@Composable
fun HeroDetailsScreen(heroId: Int?) {
    val viewModel: HeroDetailsViewModel = viewModel()
    viewModel.getHeroDetails(heroId)
    val hero = viewModel.heroState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primaryVariant)
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.40f)
                .padding(top = 16.dp, bottom = 0.dp, end = 16.dp, start = 16.dp)
        ) {
            Box {
                Image(
                    painter = rememberImagePainter(
                        data = hero.images?.lg
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = "${hero.name}",
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 8.dp)
                        .align(Alignment.BottomStart)
                )
            }
        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 0.dp, end = 16.dp, start = 16.dp)
                    .animateContentSize()
            ) {
                Column() {
                    var isExpanded by remember { mutableStateOf(false) }
                    Row(
                        modifier = Modifier.clickable { isExpanded = !isExpanded }
                    ) {
                        Text(
                            text = "Biography",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(16.dp)
                        )
                        Icon(
                            imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Expand row icon",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }
                    if (isExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 0.dp, bottom = 8.dp, end = 16.dp, start = 16.dp)
                        ) {
                            Text(
                                text = "Full name: ${hero.biography?.fullName}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Aliases: ${hero.biography?.aliases}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Place of birth: ${hero.biography?.placeOfBirth}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Alter egos: ${hero.biography?.alterEgos}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Alignment: ${hero.biography?.alignment}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Publisher: ${hero.biography?.publisher}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "First appearance: ${hero.biography?.firstAppearance}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                }
            }
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 0.dp, end = 16.dp, start = 16.dp)
                    .animateContentSize()
            ) {
                Column() {
                    var isExpanded by remember { mutableStateOf(false) }
                    Row(
                        modifier = Modifier.clickable { isExpanded = !isExpanded }
                    ) {
                        Text(
                            text = "Appearance",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(16.dp)
                        )
                        Icon(
                            imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Expand row icon",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }
                    if (isExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 0.dp, bottom = 8.dp, end = 16.dp, start = 16.dp)
                        ) {
                            Text(
                                text = "Gender: ${hero.appearance?.gender}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Race: ${hero.appearance?.race}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Height: ${hero.appearance?.height?.get(0)}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Weight: ${hero.appearance?.weight?.get(0)}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Eye color: ${hero.appearance?.eyeColor}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Hair color: ${hero.appearance?.hairColor}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                }
            }
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 0.dp, end = 16.dp, start = 16.dp)
                    .animateContentSize()
            ) {
                Column() {
                    var isExpanded by remember { mutableStateOf(false) }
                    Row(
                        modifier = Modifier.clickable { isExpanded = !isExpanded }
                    ) {
                        Text(
                            text = "Powerstats",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(16.dp)
                        )
                        Icon(
                            imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Expand row icon",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }
                    if (isExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 0.dp, bottom = 8.dp, end = 16.dp, start = 16.dp)
                        ) {
                            Text(
                                text = "Intelligence: ${hero.powerstats?.intelligence}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Strength: ${hero.powerstats?.strength}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Speed: ${hero.powerstats?.speed}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Durability: ${hero.powerstats?.durability}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Power: ${hero.powerstats?.power}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Combat: ${hero.powerstats?.combat}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                }
            }
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 0.dp, end = 16.dp, start = 16.dp)
                    .animateContentSize()
            ) {
                Column() {
                    var isExpanded by remember { mutableStateOf(false) }
                    Row(
                        modifier = Modifier.clickable { isExpanded = !isExpanded }
                    ) {
                        Text(
                            text = "Work",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(16.dp)
                        )
                        Icon(
                            imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Expand row icon",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }
                    if (isExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 0.dp, bottom = 8.dp, end = 16.dp, start = 16.dp)
                        ) {
                            Text(
                                text = "Occupation: ${hero.work?.occupation}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Base: ${hero.work?.base}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                }
            }
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 0.dp, end = 16.dp, start = 16.dp)
                    .animateContentSize()
            ) {
                Column() {
                    var isExpanded by remember { mutableStateOf(false) }
                    Row(
                        modifier = Modifier.clickable { isExpanded = !isExpanded }
                    ) {
                        Text(
                            text = "Connections",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(16.dp)
                        )
                        Icon(
                            imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Expand row icon",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }
                    if (isExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 0.dp, bottom = 8.dp, end = 16.dp, start = 16.dp)
                        ) {
                            Text(
                                text = "Group affiliation: ${hero.connections?.groupAffiliation}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Relatives: ${hero.connections?.relatives}",
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}