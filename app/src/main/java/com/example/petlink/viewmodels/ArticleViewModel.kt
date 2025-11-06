package com.example.petlink.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petlink.api.PetLinkAPI
import com.example.petlink.model.Article
import com.example.petlink.network.StateManager
import com.example.petlink.viewmodels.states.ArticleState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleViewModel: ViewModel() {
    private val articlesMutableStateFlow = MutableStateFlow(ArticleState())

    val stateFlow: StateFlow<ArticleState>
        get() = articlesMutableStateFlow.asStateFlow()

    private var articleState: ArticleState
        get() = articlesMutableStateFlow.value
        set(value) {
            articlesMutableStateFlow.value = value
        }

    init {
        viewModelScope.launch {
            getArticleList()
        }
    }

    fun getArticleList() {
        articleState = articleState.copy(isLoading = true)
        StateManager.launchCoroutine {
            articleState = try {
                articleState.copy(
                    articles = PetLinkAPI.getArticles(),
                    isLoading = false
                )
            } catch (error: Exception) {
                articleState.copy(
                    error = error,
                    isLoading = false
                )
            }
        }
    }

    fun setSelectedArticle(article: Article) {
        articleState = articleState.copy(
            selectedArticle = article
        )
    }

    fun getNewestArticle(): Article? {
        return articleState.articles.maxBy { it.date }
    }
}