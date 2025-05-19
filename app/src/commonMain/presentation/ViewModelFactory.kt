package com.geekneuron.librex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geekneuron.librex.domain.usecases.GetBooksUseCase
import com.geekneuron.librex.domain.usecases.GetBookByIdUseCase
import com.geekneuron.librex.domain.usecases.SaveBookProgressUseCase
import com.geekneuron.librex.domain.usecases.ToggleFullScreenUseCase
import com.geekneuron.librex.settings.AppSettings

class ViewModelFactory(
    private val getBooksUseCase: GetBooksUseCase,
    private val getBookByIdUseCase: GetBookByIdUseCase,
    private val saveBookProgressUseCase: SaveBookProgressUseCase,
    private val toggleFullScreenUseCase: ToggleFullScreenUseCase,
    private val appSettings: AppSettings
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(getBooksUseCase) as T
            }
            modelClass.isAssignableFrom(BookReaderViewModel::class.java) -> {
                BookReaderViewModel(getBookByIdUseCase, saveBookProgressUseCase, toggleFullScreenUseCase, appSettings) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
