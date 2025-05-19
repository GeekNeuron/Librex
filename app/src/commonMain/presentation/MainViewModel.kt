package com.geekneuron.librex.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekneuron.librex.data.BookEntity
import com.geekneuron.librex.domain.usecases.GetBooksUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val getBooksUseCase: GetBooksUseCase) : ViewModel() {
    val books = mutableStateListOf<BookEntity>()
    val showOnboarding = false

    init {
        viewModelScope.launch {
            getBooksUseCase().collect { books.clear(); books.addAll(it) }
        }
    }

    fun addBook(book: BookEntity) {
        viewModelScope.launch {
            books.add(book)
        }
    }
}
