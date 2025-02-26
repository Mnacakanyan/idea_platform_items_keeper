package com.nairi.ideaplatform.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nairi.ideaplatform.domain.usecase.ChangeQuantityUseCase
import com.nairi.ideaplatform.domain.usecase.DeleteItemUseCase
import com.nairi.ideaplatform.domain.usecase.FindByNameUseCase
import com.nairi.ideaplatform.domain.usecase.GetAllItemsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val getAllItemsUseCase: GetAllItemsUseCase,
    private val findByNameUseCase: FindByNameUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val changeQuantityUseCase: ChangeQuantityUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<UiState> = _searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) {
                getAllItemsUseCase()
            } else {
                findByNameUseCase(query)
            }
        }
        .map { items -> UiState(items = items.map { it.toPresentation() }, searchText = _searchQuery.value) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), UiState.EMPTY)

    fun onAction(action: UiAction) {
        when (action) {
            is UiAction.Search -> _searchQuery.value = action.query
            is UiAction.ClearQuery -> _searchQuery.value = ""
            is UiAction.Delete -> deleteItem(action.id)
            is UiAction.ChangeQuantity -> changeQuantity(action.id, action.item)
        }
    }

    private fun deleteItem(id: Long) {
        viewModelScope.launch {
            deleteItemUseCase(id)
        }
    }

    private fun changeQuantity(id: Long, item: Int) {
        viewModelScope.launch {
            changeQuantityUseCase(id, item)
        }
    }

}