import app.cash.sqldelight.db.SqlDriver
import com.geekneuron.librex.data.AppDatabase

expect class DatabaseDriverFactory {
    fun create(): SqlDriver
}

val Database = AppDatabase(DatabaseDriverFactory().create())
