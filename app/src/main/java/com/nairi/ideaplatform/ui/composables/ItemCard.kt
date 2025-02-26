package com.nairi.ideaplatform.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nairi.ideaplatform.R
import com.nairi.ideaplatform.ui.theme.ChangeQuantityIconTint
import com.nairi.ideaplatform.ui.theme.Red
import com.nairi.ideaplatform.ui.viewmodel.SearchItem


@OptIn(ExperimentalLayoutApi::class)
@Composable
@Stable
fun ItemCard(
    searchItem: SearchItem,
    onDeleteItem: (Long) -> Unit,
    onChangeQuantity: (itemQuantity: Int) -> Unit
) {

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showChangeQuantityDialog by remember { mutableStateOf(false) }

    val editInteractionSource = remember { MutableInteractionSource() }
    val deleteInteractionSource = remember { MutableInteractionSource() }
    val indication = rememberRipple(bounded = false)

    val onDismiss = remember {
        {
            showDeleteDialog = false
        }
    }

    val onDismissChangeQuantity = remember {
        {
            showChangeQuantityDialog = false
        }
    }



    if (showDeleteDialog) {
        DeleteDialog(
            onDismiss = onDismiss,
            onDelete = {
                onDeleteItem.invoke(searchItem.id)
                showDeleteDialog = false
            }
        )
    }


    if (showChangeQuantityDialog) {
        ChangeQuantityDialog(
            quantity = searchItem.count.toInt(),
            onDismiss = onDismissChangeQuantity,
            onConfirm = { newQuantity ->
                showChangeQuantityDialog = false
                onChangeQuantity.invoke(newQuantity)
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            key(searchItem.name){
                Text(
                    text = searchItem.name,
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.W500
                )
            }
            Row(
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = null,
                    tint = ChangeQuantityIconTint,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable(
                            interactionSource = editInteractionSource,
                            indication = indication
                        ) {
                            showChangeQuantityDialog = true
                        }
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Red,
                    modifier = Modifier
                        .clickable(
                            interactionSource = deleteInteractionSource,
                            indication = indication
                        ) {
                            showDeleteDialog = true
                        }
                )
            }
        }

        if (searchItem.desc.isNotEmpty()) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                searchItem.desc.forEach {
                    AssistChip(
                        onClick = { },
                        label = { Text(text = it) },
                        modifier = Modifier.padding(end = 4.dp)
                    )
                }
            }
        }

        Row(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 16.dp),
        ) {
            Column(
                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    text = stringResource(R.string.in_stock),
                    fontSize = 16.sp
                )
                Text(
                    text = searchItem.count
                )
            }
            Column(
                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    text = stringResource(R.string.date),
                    fontSize = 16.sp
                )
                Text(
                    text = searchItem.date
                )
            }
        }
    }
}
