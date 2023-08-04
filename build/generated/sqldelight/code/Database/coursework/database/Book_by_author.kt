package coursework.database

import kotlin.Long
import kotlin.String

public data class Book_by_author(
  public val TITLE: String,
  public val PUBLISHER_NAME: String,
  public val YEAR_OF_PUBLICATION: Long,
  public val SUBJECT: String,
  public val AUTHOR_ID: Long?
) {
  public override fun toString(): String = """
  |Book_by_author [
  |  TITLE: $TITLE
  |  PUBLISHER_NAME: $PUBLISHER_NAME
  |  YEAR_OF_PUBLICATION: $YEAR_OF_PUBLICATION
  |  SUBJECT: $SUBJECT
  |  AUTHOR_ID: $AUTHOR_ID
  |]
  """.trimMargin()
}
