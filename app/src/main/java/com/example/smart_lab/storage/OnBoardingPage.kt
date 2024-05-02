package com.example.smart_lab.storage

import androidx.annotation.DrawableRes
import com.example.smart_lab.R

sealed class OnBoardingPage (
    @DrawableRes
    val image: Int,
    val buttonText: String,
    val title: String,
    val titleDescription: String
) {
    object First : OnBoardingPage(
        image = R.drawable.on_board_first_image,
        buttonText = "Пропустить",
        title = "Анализы",
        titleDescription = "Экспресс сбор и получение проб"
    )
    object Second : OnBoardingPage(
        image = R.drawable.on_board_second_image,
        buttonText = "Пропустить",
        title = "Уведомления",
        titleDescription = "Вы быстро узнаете о результатах"
    )
    object Third : OnBoardingPage(
        image = R.drawable.on_board_third_image,
        buttonText = "Завершить",
        title = "Мониторинг",
        titleDescription = "Наши врачи всегда наблюдают \n" +
                "за вашими показателями здоровья"
    )
}