import app.cash.sqldelight.db.sqlite.driver.JdbcSqliteDriver
import com.geekneuron.librex.data.AppDatabase
import java.io.File

class DatabaseDriverFactory {
    fun create(): SqlDriver {
        val schema = AppDatabase.Schema
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.JDBC_SQLITE_3)
        schema.create(driver)
        val dbPath = File(System.getProperty("user.dir"), "librex.db").absolutePath
        return JdbcSqliteDriver("jdbc:sqlite:$dbPath")
    }
}
