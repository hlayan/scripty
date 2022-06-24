package com.hlayan.scripty.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hlayan.scripty.ui.components.ToggleButton
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val clipboardManager = LocalClipboardManager.current

    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .clipToBounds(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            ToggleButton(viewModel.inputType.value) {
                viewModel.updateInputType(it)
            }

            Spacer(Modifier.size(8.dp))

            OutlinedTextField(
                value = viewModel.inputValue.value,
                onValueChange = viewModel::updateInputValue,
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                trailingIcon = {
                    IconButton(onClick = {
                        clipboardManager.setText(viewModel.inputValue.value.annotatedString)

                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Copied to Clipboard",
                                actionLabel = "Close"
                            )
                        }
                    }) {
                        Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null)
                    }
                }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    Box(Modifier.fillMaxSize()) {
        HomeScreen()
    }
}