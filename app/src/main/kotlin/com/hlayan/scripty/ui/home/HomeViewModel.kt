package com.hlayan.scripty.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.hlayan.scripty.data.local.InputType
import com.hlayan.scripty.extensions.scriptRange
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _inputType = mutableStateOf(InputType.NORMAL)
    val inputType: State<InputType> get() = _inputType

    private val _inputValue = mutableStateOf(TextFieldValue(""))
    val inputValue: State<TextFieldValue> get() = _inputValue

    fun updateInputType(newType: InputType) {
        Timber.i(_inputType.value.toString())
        _inputType.value = newType
    }

    fun updateInputValue(newValue: TextFieldValue) {

        Timber.i(newValue.toString())

        val start = _inputValue.value.selection.min
        val end = newValue.selection.max

        val oldText = _inputValue.value.text

        fun getScripted(): TextFieldValue {
            return newValue.run { copy(text.scriptRange(start, end, _inputType.value)) }
        }

        _inputValue.value = when {
            _inputType.value == InputType.NORMAL -> newValue
            newValue.text.length > oldText.length -> getScripted()
            newValue.text.length < oldText.length -> {
                if (_inputValue.value.selection.collapsed || start == end) newValue
                else getScripted()

            }
            newValue.text.length == oldText.length -> {
                when {
                    start == end -> {
                        _inputValue.value.copy(composition = newValue.composition)
                    }
                    !newValue.selection.collapsed || newValue.text == oldText -> newValue
                    else -> getScripted()
                }
            }
            else -> newValue
        }
    }
}