package com.example.test_app.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.test_app.R

enum class Route(
    val argumentKey: String? = null,
    @StringRes val labelResId: Int? = null, // Used for BottomNavBar
    @DrawableRes val iconResId: Int? = null, // Used for BottomNavBar
) {
    Home(labelResId = R.string.bottom_nav_bar_item_home, iconResId = R.drawable.ic_home),
    News(labelResId = R.string.bottom_nav_bar_item_news, iconResId = R.drawable.ic_news),
    Search(labelResId = R.string.bottom_nav_bar_item_search, iconResId = R.drawable.ic_search),
    Profile(labelResId = R.string.bottom_nav_bar_item_profile, iconResId = R.drawable.ic_profile),
    More(labelResId = R.string.bottom_nav_bar_item_more, iconResId = R.drawable.ic_more),

    WebView(argumentKey = "url");

    val routeWithArgumentKey: String = "$name/{$argumentKey}"

    fun getFullRoute(argument: String) = "$name/$argument"

    companion object {
        val bottomNavItems: List<Route>
            get() = listOf(Home, News, Search, Profile, More)

        val bottomNavItemsRoutes: List<String>
            get() = listOf(Home.name, News.name, Search.name, Profile.name, More.name)
    }
}
