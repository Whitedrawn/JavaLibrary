package coursework.database

import kotlin.Long
import kotlin.String

public data class AUTHOR(
  public val id: Long,
  public val FIRST_NAME: String,
  public val LAST_NAME: String
) {
  public override fun toString(): String = """
  |AUTHOR [
  |  id: $id
  |  FIRST_NAME: $FIRST_NAME
  |  LAST_NAME: $LAST_NAME
  |]
  """.trimMargin()
}
