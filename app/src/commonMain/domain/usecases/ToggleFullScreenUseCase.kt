package com.geekneuron.librex.domain.usecases

import com.geekneuron.librex.settings.AppSettings

class ToggleFullScreenUseCase(private val appSettings: AppSettings) {
    operator fun invoke() {
        appSettings.isFullScreen = !appSettings.isFullScreen
    }
}
