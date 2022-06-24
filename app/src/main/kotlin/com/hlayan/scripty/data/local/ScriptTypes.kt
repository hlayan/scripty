package com.hlayan.scripty.data.local

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Subscript
import androidx.compose.material.icons.filled.Superscript
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.ui.graphics.vector.ImageVector

enum class InputType(val icon: ImageVector) {
    NORMAL(Icons.Default.TextFormat),
    SUBSCRIPT(Icons.Default.Subscript),
    SUPERSCRIPT(Icons.Default.Superscript)
}