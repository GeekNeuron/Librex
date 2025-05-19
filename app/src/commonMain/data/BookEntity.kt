import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.db.SqliteDriver
import app.cash.sqldelight.runtime.coroutines.asFlow
import app.cash.sqldelight.runtime.coroutines.mapToList
import com.geekneuron.librex.data.AppDatabase
import kotlinx.coroutines.flow.Flow

data class BookEntity(
    val id: Long,
    val title: String,
    val author: String,
    val coverUrl: String,
    val contentUrl: String,
    val currentPage: Int = 0,
    val totalTime: Long = 0,
    val lastReadTime: Long = 0
)

expect class DatabaseDriverFactory {
    fun create(): SqlDriver
}

val Database = AppDatabase(DatabaseDriverFactory().create())
