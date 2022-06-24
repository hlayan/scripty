package com.hlayan.scripty.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.hlayan.scripty.data.local.InputType
import com.hlayan.scripty.ui.theme.DefaultCornerSize

@Composable
fun ToggleButton(inputType: InputType, onSelect: (InputType) -> Unit) {

    val startShape = remember {
        RoundedCornerShape(topStart = DefaultCornerSize, bottomStart = DefaultCornerSize)
    }
    val lastShape = remember {
        RoundedCornerShape(topEnd = DefaultCornerSize, bottomEnd = DefaultCornerSize)
    }

    val colorOnSelected = MaterialTheme.colors.primary.copy(0.12f)
    val borderStrokeOnSelected = BorderStroke(1.dp, MaterialTheme.colors.primary)

    Row {
        val inputTypes = remember { InputType.values() }
        inputTypes.forEachIndexed { index, scriptFormat ->

            fun isSelect() = inputType.ordinal == index

            OutlinedButton(
                onClick = {
                    if (inputType != scriptFormat) onSelect(scriptFormat)
                },
                border = if (isSelect()) borderStrokeOnSelected else ButtonDefaults.outlinedBorder,
                shape = when (index) {
                    0 -> startShape
                    inputTypes.lastIndex -> lastShape
                    else -> RectangleShape
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = if (isSelect()) colorOnSelected else MaterialTheme.colors.surface
                ),
                modifier = if (index == 0) {
                    Modifier.zIndex(if (isSelect()) 1f else 0f)
                } else {
                    Modifier
                        .offset(x = (-1 * index).dp)
                        .zIndex(if (isSelect()) 1f else 0f)
                },
            ) {
                Icon(
                    imageVector = scriptFormat.icon,
                    contentDescription = null,
                    tint = if (isSelect()) MaterialTheme.colors.primary else Color.Gray
                )
            }
        }
    }
}