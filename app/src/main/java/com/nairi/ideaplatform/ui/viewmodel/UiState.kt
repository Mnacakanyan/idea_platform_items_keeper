package com.nairi.ideaplatform.ui.viewmodel

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.nairi.ideaplatform.domain.model.Item
import com.nairi.ideaplatform.utils.toDate
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

data class UiState(
    val searchText: String,
    val items: List<SearchItem>,
) {
    companion object {
        val EMPTY = UiState(
            searchText = "",
            items = persistentListOf(),
        )
    }
}

@Immutable
data class SearchItem(
    val id: Long,
    val name: String,
    val desc: ImmutableList<String>,
    val count: String,
    val date: String,
)

@Stable
fun Item.toPresentation(): SearchItem {
    return SearchItem(
        id = id,
        name = name,
        desc = desc.toImmutableList(),
        count = count.toString(),
        date = date.toDate(),
    )
}
