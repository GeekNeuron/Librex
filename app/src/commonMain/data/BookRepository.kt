import com.geekneuron.librex.data.AppDatabase
import com.geekneuron.librex.data.BookEntity
import kotlinx.coroutines.flow.Flow

class BookRepository(private val database: AppDatabase) {
    fun getAllBooks(): Flow<List<BookEntity>> {
        return database.bookQueries.selectAll().asFlow().mapToList()
    }

    suspend fun addBook(book: BookEntity) {
        database.bookQueries.insert(
            id = book.id,
            title = book.title,
            author = book.author,
            coverUrl = book.coverUrl,
            contentUrl = book.contentUrl,
            currentPage = book.currentPage,
            totalTime = book.totalTime,
            lastReadTime = book.lastReadTime
        )
    }

    suspend fun updateBook(book: BookEntity) {
        database.bookQueries.update(
            id = book.id,
            title = book.title,
            author = book.author,
            coverUrl = book.coverUrl,
            contentUrl = book.contentUrl,
            currentPage = book.currentPage,
            totalTime = book.totalTime,
            lastReadTime = book.lastReadTime
        )
    }

    suspend fun getBookById(bookId: Long): BookEntity? {
        return database.bookQueries.selectById(bookId).executeAsOneOrNull()?.toBookEntity()
    }
}

fun AppDatabase.Book.toBookEntity() = BookEntity(
    id = id,
    title = title,
    author = author,
    coverUrl = coverUrl,
    contentUrl = contentUrl,
    currentPage = currentPage,
    totalTime = totalTime,
    lastReadTime = lastReadTime
)
