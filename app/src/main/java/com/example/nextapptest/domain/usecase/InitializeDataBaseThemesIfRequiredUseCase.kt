package com.example.nextapptest.domain.usecase

import com.example.nextapptest.R
import com.example.nextapptest.core.Status
import com.example.nextapptest.domain.model.ThemeModel
import com.example.nextapptest.domain.repository.ThemesService
import javax.inject.Inject

interface InitializeDataBaseThemesIfRequiredUseCase {
    suspend operator fun invoke(): Status<Unit>
}

class InitializeDataBaseThemesIfRequiredUseCaseImp @Inject constructor(
    private val themesService: ThemesService
) : InitializeDataBaseThemesIfRequiredUseCase {

    override suspend fun invoke(): Status<Unit> {
        fun generateThemes(): List<ThemeModel> {
            val imageList = listOf(
                R.drawable.ic_test_keyboard_1,
                R.drawable.ic_test_keyboard_2,
                R.drawable.ic_test_keyboard_3,
                R.drawable.ic_test_keyboard_4,
                R.drawable.ic_test_keyboard_5,
                R.drawable.ic_test_keyboard_6,
                R.drawable.ic_test_keyboard_7,
                R.drawable.ic_test_keyboard_8,
                R.drawable.ic_test_keyboard_9,
                R.drawable.ic_test_keyboard_10,
                R.drawable.ic_test_keyboard_11,
                R.drawable.ic_test_keyboard_12,
                R.drawable.ic_test_keyboard_13,
                R.drawable.ic_test_keyboard_14,
                R.drawable.ic_test_keyboard_15,
                R.drawable.ic_test_keyboard_16
            )

            val themeList = imageList.mapIndexed { index, imageId ->
                ThemeModel(
                    id = index,
                    wasUnlocked = when (index % 6) {
                        0, 3 -> false
                        else -> true
                    },
                    isSelected = false,
                    imageId = imageId,
                    wasAlreadyInited = true
                )
            }
            return themeList
        }
        return themesService.initializeThemesInDb(generateThemes())
    }
}