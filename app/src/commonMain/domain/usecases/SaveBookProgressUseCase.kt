package com.geekneuron.librex.domain.usecases

import com.geekneuron.librex.data.BookEntity
import com.geekneuron.librex.data.BookRepository

class SaveBookProgressUseCase(private val repository: BookRepository) {
    suspend operator fun invoke(book: BookEntity) {
        repository.updateBook(book)
    }
}
