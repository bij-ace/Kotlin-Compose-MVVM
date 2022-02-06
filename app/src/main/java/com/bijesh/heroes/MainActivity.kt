package com.bijesh.heroes

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.bijesh.heroes.ui.Drawer
import com.bijesh.heroes.ui.TopBar
import com.bijesh.heroes.ui.composables.herodetails.HeroDetailsScreen
import com.bijesh.heroes.ui.composables.herolist.HeroListScreen
import com.bijesh.heroes.ui.composables.herolist.HeroListViewModel
import com.bijesh.heroes.ui.composables.search.SearchScreen
import com.bijesh.heroes.ui.theme.HeroesTheme
import com.bijesh.heroes.utils.factory.HeroListViewModelInjector
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this is an example to create a factory if we need to pass additional parameters to viewmodel
        // if not, viewmodel can be directly instantiated as shown in HeroDetailsScreen
        val factory = HeroListViewModelInjector(application).provideHeroListViewModelFactory()
        val viewModel = ViewModelProvider(this, factory)[HeroListViewModel::class.java]
        setContent {
            HeroesTheme {
                MainScreen(viewModel)
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MainScreen(viewModel: HeroListViewModel?) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberAnimatedNavController()
    val isSecondaryScreen = remember { mutableStateOf(false)}
    // If you want the drawer from the right side, uncomment the following
    // CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState, navController = navController, isSecondaryScreen = isSecondaryScreen) },
        drawerBackgroundColor = MaterialTheme.colors.primary,
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
        },
    ) {
        AnimatedNavHost(navController = navController, startDestination = "search_screen") {
            composable(
                route = "search_screen"
            ) {
                SearchScreen(viewModel) {
                    val heroId = it.id
                    navController.navigate("hero_details_screen/$heroId")
                    isSecondaryScreen.value = true
                }
            }
            composable(
                route = "hero_list_screen/{universe}",
                arguments = listOf(navArgument("universe") {
                    type = NavType.StringType
                })
            ) {
                val universe = it.arguments?.getString("universe")
                HeroListScreen(viewModel, universe) {
                    val heroId = it.id
                    navController.navigate("hero_details_screen/$heroId")
                    isSecondaryScreen.value = true
                }
            }
            composable(
                route = "hero_details_screen/{heroId}",
                arguments = listOf(navArgument("heroId") {
                    type = NavType.IntType
                }),
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = {Resources.getSystem().displayMetrics.widthPixels},
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = {Resources.getSystem().displayMetrics.widthPixels},
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(300))
                }
            ) {
                val heroId = it.arguments?.getInt("heroId")
                HeroDetailsScreen(heroId)
            }
        }
    }
    // }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(null)
}