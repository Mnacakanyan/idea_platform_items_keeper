package com.nairi.ideaplatform.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.nairi.ideaplatform.R

@Composable
fun DeleteDialog(
    onDismiss: () -> Unit = {},
    onDelete: () -> Unit = {}
) {


    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null
            )
        },
        title = {
            Text(
                text = stringResource(R.string.delete_item_dialog_title)
            )
        },
        text = {
            Text(
                text = stringResource(R.string.delete_item_dialog_text)
            )
        },
        confirmButton = {
            TextButton(
                onClick = onDelete,
            ) {
                Text(
                    text = stringResource(R.string.yes),
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
            ) {
                Text(
                    text = stringResource(R.string.no),
                )
            }

        }
    )
}