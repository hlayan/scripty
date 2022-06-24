package com.hlayan.scripty.extensions

import com.hlayan.scripty.data.local.InputType
import com.hlayan.scripty.data.local.subscripts
import com.hlayan.scripty.data.local.superscripts

val CharSequence.superScript: String
    get() {
        val charList = map { superscripts[it] ?: it }
        return String(charList.toCharArray())
    }

val CharSequence.subScript: String
    get() {
        val charList = map { subscripts[it] ?: it }
        return String(charList.toCharArray())
    }

fun String.getScripted(inputType: InputType): String {
    return when (inputType) {
        InputType.SUPERSCRIPT -> superScript
        InputType.SUBSCRIPT -> subScript
        InputType.NORMAL -> this
    }
}

fun String.scriptRange(start: Int, end: Int, inputType: InputType): String {
    val scripted = substring(start, end).getScripted(inputType)
    return replaceRange(start, end, scripted)
}