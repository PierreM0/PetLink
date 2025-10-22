package com.example.petlink.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petlink.api.PetLinkAPI
import com.example.petlink.model.Article
import com.example.petlink.network.StateManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleViewModel: ViewModel() {
    private val articlesMutableStateFlow = MutableStateFlow(ArticleState())

    val stateFlow: StateFlow<ArticleState>
        get() = articlesMutableStateFlow.asStateFlow()

    private var ArticleState: ArticleState
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
        ArticleState = ArticleState.copy(isLoading = true)
        StateManager.launchCoroutine {
            ArticleState = try {
                ArticleState.copy(
                    articles = PetLinkAPI.getArticles(),
                    isLoading = false
                )
            } catch (error: Exception) {
                ArticleState.copy(
                    error = error,
                    isLoading = false
                )
            }
        }
    }

    fun setSelectedArticle(article: Article) {
        ArticleState = ArticleState.copy(
            selectedArticle = article
        )
    }
}