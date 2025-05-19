package com.geekneuron.librex.domain.usecases

import com.geekneuron.librex.data.BookEntity
import com.geekneuron.librex.data.BookRepository
import kotlinx.coroutines.flow.Flow

class GetBooksUseCase(private val repository: BookRepository) {
    operator fun invoke(): Flow<List<BookEntity>> {
        return repository.getAllBooks()
    }
}
