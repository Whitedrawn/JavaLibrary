package coursework.database

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Long
import kotlin.String
import kotlin.Unit

public interface CWQueries : Transacter {
  public fun <T : Any> allBooks(mapper: (
    id: Long,
    TITLE: String,
    PUBLISHER_NAME: String,
    YEAR_OF_PUBLICATION: Long,
    SUBJECT: String,
    AUTHOR_ID: Long?
  ) -> T): Query<T>

  public fun allBooks(): Query<BOOK>

  public fun <T : Any> allAuthors(mapper: (
    id: Long,
    FIRST_NAME: String,
    LAST_NAME: String
  ) -> T): Query<T>

  public fun allAuthors(): Query<AUTHOR>

  public fun <T : Any> book_by_author(AUTHOR_ID: Long?, mapper: (
    TITLE: String,
    PUBLISHER_NAME: String,
    YEAR_OF_PUBLICATION: Long,
    SUBJECT: String,
    AUTHOR_ID: Long?
  ) -> T): Query<T>

  public fun book_by_author(AUTHOR_ID: Long?): Query<Book_by_author>

  public fun insertBook(
    TITLE: String,
    PUBLISHER_NAME: String,
    YEAR_OF_PUBLICATION: Long,
    SUBJECT: String,
    AUTHOR_ID: Long?
  ): Unit

  public fun insertAuthor(FIRST_NAME: String, LAST_NAME: String): Unit
}
