package com.example.test_app.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.test_app.R

enum class Route(
    val argumentKey: String? = null,
    @StringRes val labelResId: Int? = null, // Used for BottomNavBar
    @DrawableRes val iconResId: Int? = null, // Used for BottomNavBar
) {
    HomeTab(labelResId = R.string.bottom_nav_bar_item_home, iconResId = R.drawable.ic_home),
    NewsTab(labelResId = R.string.bottom_nav_bar_item_news, iconResId = R.drawable.ic_news),
    SearchTab(labelResId = R.string.bottom_nav_bar_item_search, iconResId = R.drawable.ic_search),
    ProfileTab(labelResId = R.string.bottom_nav_bar_item_profile, iconResId = R.drawable.ic_profile),
    MoreTab(labelResId = R.string.bottom_nav_bar_item_more, iconResId = R.drawable.ic_more),

    Search,
    Filter,
    SearchIn,
    SortByBottomSheet,

    WebView(argumentKey = "url");

    val routeWithArgumentKey: String = "$name/{$argumentKey}"

    fun getFullRoute(argument: String) = "$name/$argument"

    companion object {
        val bottomNavItems: List<Route>
            get() = listOf(HomeTab, NewsTab, SearchTab, ProfileTab, MoreTab)

        val bottomNavItemsRoutes: List<String>
            get() = listOf(HomeTab.name, NewsTab.name, Search.name, ProfileTab.name, MoreTab.name)
    }
}
