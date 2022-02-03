package com.bijesh.heroes.model

import com.bijesh.heroes.R

sealed class NavDrawerItem(var route: String, var key: String, var icon: Int, var title: String) {
    object Search : NavDrawerItem("search_screen", "", android.R.drawable.ic_menu_search, "Search")
    object DC : NavDrawerItem("hero_list_screen", "DC Comics", R.drawable.dc, "DC Comics")
    object Marvel : NavDrawerItem("hero_list_screen", "Marvel Comics", R.drawable.marvel, "Marvel Comics")
}
