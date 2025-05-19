import app.cash.sqldelight.db.sqlite.driver.NSLocalizedDescription
import app.cash.sqldelight.db.sqlite.driver.NSLocalizedFailureReason
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.geekneuron.librex.data.AppDatabase
import platform.Foundation.NSBundle

class DatabaseDriverFactory {
    fun create(): SqlDriver {
        val schema = AppDatabase.Schema
        val bundle = NSBundle.mainBundle
        val path = "${bundle.bundlePath}/librex.db"
        return NativeSqliteDriver(schema, path)
    }
}
