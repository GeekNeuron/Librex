package com.geekneuron.librex.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekneuron.librex.data.BookEntity
import com.geekneuron.librex.domain.usecases.GetBookByIdUseCase
import com.geekneuron.librex.domain.usecases.SaveBookProgressUseCase
import com.geekneuron.librex.domain.usecases.ToggleFullScreenUseCase
import com.geekneuron.librex.settings.AppSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File

class BookReaderViewModel(
    private val getBookByIdUseCase: GetBookByIdUseCase,
    private val saveBookProgressUseCase: SaveBookProgressUseCase,
    private val toggleFullScreenUseCase: ToggleFullScreenUseCase,
    private val appSettings: AppSettings
) : ViewModel() {
    private val _book = MutableStateFlow<BookEntity?>(null)
    val book: StateFlow<BookEntity?> = _book

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage

    private val _totalPages = MutableStateFlow(0)
    val totalPages: StateFlow<Int> = _totalPages

    private val _isFullScreen = MutableStateFlow(appSettings.isFullScreen)
    val isFullScreen: StateFlow<Boolean> = _isFullScreen

    init {
        viewModelScope.launch {
            // Load book details from repository
        }
    }

    fun loadBook(bookId: Long) {
        viewModelScope.launch {
            _book.value = getBookByIdUseCase(bookId)
            _currentPage.value = _book.value?.currentPage ?: 0
        }
    }

    fun saveProgress(book: BookEntity) {
        viewModelScope.launch {
            val updatedBook = book.copy(
                currentPage = _currentPage.value,
                totalTime = System.currentTimeMillis() - book.lastReadTime,
                lastReadTime = System.currentTimeMillis()
            )
            saveBookProgressUseCase(updatedBook)
        }
    }

    fun toggleFullScreen() {
        _isFullScreen.value = !_isFullScreen.value
        toggleFullScreenUseCase()
    }

    fun setCurrentPage(page: Int) {
        _currentPage.value = page
    }

    fun setTotalPages(pages: Int) {
        _totalPages.value = pages
    }
}
