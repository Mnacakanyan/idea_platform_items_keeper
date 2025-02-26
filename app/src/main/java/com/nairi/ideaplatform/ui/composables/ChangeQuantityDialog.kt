package com.nairi.ideaplatform.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nairi.ideaplatform.R

@Composable
fun ChangeQuantityDialog(
    quantity: Int,
    onDismiss: () -> Unit = {},
    onConfirm: (Int) -> Unit = {}
) {

    val ripple = rememberRipple(bounded = false)

    var newQuantity by remember { mutableIntStateOf(quantity) }

    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null
            )
        },
        title = {
            Text(
                text = stringResource(R.string.item_quantity),
                fontSize = 16.sp
            )
        },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.RemoveCircleOutline,
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .clickable(
                            onClick = {
                                if (newQuantity >= 1) {
                                    newQuantity--
                                }
                            },
                            interactionSource = null,
                            indication = ripple
                        )
                )

                Text(
                    text = newQuantity.toString(),
                    fontSize = 30.sp,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Icon(
                    imageVector = Icons.Default.AddCircleOutline,
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .clickable(
                            onClick = {
                                newQuantity++
                            },
                            interactionSource = null,
                            indication = ripple
                        )
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm.invoke(newQuantity)
                },
            ) {
                Text(
                    text = stringResource(R.string.accept),
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                )
            }
        },
    )

}