package com.nairi.ideaplatform.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nairi.ideaplatform.R
import com.nairi.ideaplatform.ui.viewmodel.UiAction
import com.nairi.ideaplatform.ui.viewmodel.UiState


@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    uiState: UiState,
    onAction: (UiAction) -> Unit
) {
    var searchText by remember { mutableStateOf(uiState.searchText) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val scrollState = rememberLazyListState()


    LaunchedEffect(uiState.searchText) {
        searchText = uiState.searchText
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(
            interactionSource = null,
            indication = null
        ) {
            keyboardController?.hide()
            focusManager.clearFocus()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 8.dp),
            state = scrollState
        ) {
            item {
                TextField(
                    value = searchText,
                    onValueChange = { newValue ->
                        searchText = newValue
                        onAction(UiAction.Search(newValue))
                    },
                    placeholder = { Text(text = stringResource(id = R.string.search_items)) },
                    trailingIcon = {
                        if (searchText.isNotEmpty()) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = null,
                                modifier = Modifier.clickable {
                                    searchText = ""
                                    onAction(UiAction.ClearQuery)
                                }
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(4.dp)
                        ),

                    )
            }

            items(uiState.items, key = { it.id }) { searchItem ->
                ItemCard(
                    searchItem = searchItem,
                    onDeleteItem = {
                        onAction(UiAction.Delete(searchItem.id))
                    },
                    onChangeQuantity = { itemQuantity ->
                        onAction(UiAction.ChangeQuantity(searchItem.id, itemQuantity))
                    }
                )

            }
        }
    }
}

