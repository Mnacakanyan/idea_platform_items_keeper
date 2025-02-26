package com.nairi.ideaplatform.ui.viewmodel

interface UiAction {
    data class Search(val query: String) : UiAction
    data class Delete(val id: Long) : UiAction
    data class ChangeQuantity(val id: Long, val item: Int) : UiAction
    data object ClearQuery : UiAction
}