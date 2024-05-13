package com.example.smart_lab.models

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf

@Stable
class FilterButtonModel (
    val text: String,
    enabled: Boolean = false
){
    val enabled = mutableStateOf(enabled)
}

val filterButtonTextList = listOf(
    FilterButtonModel(text = "Популярные"),
    FilterButtonModel(text = "Covid"),
    FilterButtonModel(text = "Комплексные"),
    FilterButtonModel(text = "Чекапы"),
    FilterButtonModel(text = "Биохимия"),
    FilterButtonModel(text = "Гормоны"),
    FilterButtonModel(text = "Иммунитет"),
    FilterButtonModel(text = "Витамины"),
    FilterButtonModel(text = "Аллергены"),
    FilterButtonModel(text = "Анализ крови"),
    FilterButtonModel(text = "Анализ мочи"),
    FilterButtonModel(text = "Анализ кала"),
    FilterButtonModel(text = "Только в клиннике"),
)