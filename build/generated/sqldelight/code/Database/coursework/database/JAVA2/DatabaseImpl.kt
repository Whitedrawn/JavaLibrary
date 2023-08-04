package coursework.database.JAVA2

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import coursework.database.AUTHOR
import coursework.database.BOOK
import coursework.database.Book_by_author
import coursework.database.CWQueries
import coursework.database.Database
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<Database>.schema: SqlDriver.Schema
  get() = DatabaseImpl.Schema

internal fun KClass<Database>.newInstance(driver: SqlDriver): Database = DatabaseImpl(driver)

private class DatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), Database {
  public override val cWQueries: CWQueriesImpl = CWQueriesImpl(this, driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS BOOK (
          |                                        id INTEGER PRIMARY KEY AUTOINCREMENT,
          |                                        TITLE TEXT NOT NULL,
          |                                        PUBLISHER_NAME TEXT NOT NULL,
          |                                        YEAR_OF_PUBLICATION INTEGER NOT NULL,
          |                                        SUBJECT TEXT NOT NULL,
          |                                        AUTHOR_ID INTEGER,
          |                                        FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR(id)
          |
          |
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS AUTHOR (
          |id INTEGER PRIMARY KEY AUTOINCREMENT,
          |FIRST_NAME TEXT NOT NULL,
          |LAST_NAME TEXT NOT NULL
          |
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class CWQueriesImpl(
  private val database: DatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), CWQueries {
  internal val allBooks: MutableList<Query<*>> = copyOnWriteList()

  internal val allAuthors: MutableList<Query<*>> = copyOnWriteList()

  internal val book_by_author: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> allBooks(mapper: (
    id: Long,
    TITLE: String,
    PUBLISHER_NAME: String,
    YEAR_OF_PUBLICATION: Long,
    SUBJECT: String,
    AUTHOR_ID: Long?
  ) -> T): Query<T> = Query(44674632, allBooks, driver, "CW.sq", "allBooks", """
  |SELECT *
  |FROM BOOK
  """.trimMargin()) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getString(4)!!,
      cursor.getLong(5)
    )
  }

  public override fun allBooks(): Query<BOOK> = allBooks { id, TITLE, PUBLISHER_NAME,
      YEAR_OF_PUBLICATION, SUBJECT, AUTHOR_ID ->
    BOOK(
      id,
      TITLE,
      PUBLISHER_NAME,
      YEAR_OF_PUBLICATION,
      SUBJECT,
      AUTHOR_ID
    )
  }

  public override fun <T : Any> allAuthors(mapper: (
    id: Long,
    FIRST_NAME: String,
    LAST_NAME: String
  ) -> T): Query<T> = Query(-728552346, allAuthors, driver, "CW.sq", "allAuthors", """
  |SELECT *
  |FROM AUTHOR
  """.trimMargin()) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!
    )
  }

  public override fun allAuthors(): Query<AUTHOR> = allAuthors { id, FIRST_NAME, LAST_NAME ->
    AUTHOR(
      id,
      FIRST_NAME,
      LAST_NAME
    )
  }

  public override fun <T : Any> book_by_author(AUTHOR_ID: Long?, mapper: (
    TITLE: String,
    PUBLISHER_NAME: String,
    YEAR_OF_PUBLICATION: Long,
    SUBJECT: String,
    AUTHOR_ID: Long?
  ) -> T): Query<T> = Book_by_authorQuery(AUTHOR_ID) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getLong(2)!!,
      cursor.getString(3)!!,
      cursor.getLong(4)
    )
  }

  public override fun book_by_author(AUTHOR_ID: Long?): Query<Book_by_author> =
      book_by_author(AUTHOR_ID) { TITLE, PUBLISHER_NAME, YEAR_OF_PUBLICATION, SUBJECT, AUTHOR_ID_ ->
    Book_by_author(
      TITLE,
      PUBLISHER_NAME,
      YEAR_OF_PUBLICATION,
      SUBJECT,
      AUTHOR_ID_
    )
  }

  public override fun insertBook(
    TITLE: String,
    PUBLISHER_NAME: String,
    YEAR_OF_PUBLICATION: Long,
    SUBJECT: String,
    AUTHOR_ID: Long?
  ): Unit {
    driver.execute(-507489727,
        """INSERT INTO BOOK ( TITLE, PUBLISHER_NAME, YEAR_OF_PUBLICATION, SUBJECT, AUTHOR_ID) VALUES ( ?, ?, ?, ?,?)""",
        5) {
      bindString(1, TITLE)
      bindString(2, PUBLISHER_NAME)
      bindLong(3, YEAR_OF_PUBLICATION)
      bindString(4, SUBJECT)
      bindLong(5, AUTHOR_ID)
    }
    notifyQueries(-507489727, {database.cWQueries.allBooks + database.cWQueries.book_by_author})
  }

  public override fun insertAuthor(FIRST_NAME: String, LAST_NAME: String): Unit {
    driver.execute(1905705699, """INSERT INTO AUTHOR (FIRST_NAME, LAST_NAME) VALUES (?, ?)""", 2) {
      bindString(1, FIRST_NAME)
      bindString(2, LAST_NAME)
    }
    notifyQueries(1905705699, {database.cWQueries.allAuthors + database.cWQueries.book_by_author})
  }

  private inner class Book_by_authorQuery<out T : Any>(
    public val AUTHOR_ID: Long?,
    mapper: (SqlCursor) -> T
  ) : Query<T>(book_by_author, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(null, """
    |SELECT
    |    BOOK.TITLE,
    |    BOOK.PUBLISHER_NAME,
    |    BOOK.YEAR_OF_PUBLICATION,
    |    BOOK.SUBJECT,
    |    BOOK.AUTHOR_ID
    |FROM BOOK
    |INNER JOIN AUTHOR
    |ON BOOK.AUTHOR_ID = AUTHOR.id
    |WHERE BOOK.AUTHOR_ID ${ if (AUTHOR_ID == null) "IS" else "=" } ?
    """.trimMargin(), 1) {
      bindLong(1, AUTHOR_ID)
    }

    public override fun toString(): String = "CW.sq:book_by_author"
  }
}
