package coursework.database

import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import coursework.database.JAVA2.newInstance
import coursework.database.JAVA2.schema

public interface Database : Transacter {
  public val cWQueries: CWQueries

  public companion object {
    public val Schema: SqlDriver.Schema
      get() = Database::class.schema

    public operator fun invoke(driver: SqlDriver): Database = Database::class.newInstance(driver)
  }
}
