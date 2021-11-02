package com.example.test_app.feature.news

import com.example.test_app.api.entity.Articles
import com.example.test_app.ui.DataScreenState

data class NewsScreenState(
    override val data: Articles? = null,
    override val isLoading: Boolean = false,
    override val isError: Boolean = false
) : DataScreenState<Articles>
