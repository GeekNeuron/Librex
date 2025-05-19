package com.geekneuron.librex.domain.usecases

import com.geekneuron.librex.data.BookEntity
import com.geekneuron.librex.data.BookRepository

class GetBookByIdUseCase(private val repository: BookRepository) {
    suspend operator fun invoke(bookId: Long): BookEntity? {
        return repository.getBookById(bookId)
    }
}
