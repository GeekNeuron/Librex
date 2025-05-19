import android.content.Context
import app.cash.sqldelight.db.android.AndroidSqliteDriver
import com.geekneuron.librex.data.AppDatabase

class DatabaseDriverFactory(private val context: Context) {
    fun create(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "librex.db")
    }
}
