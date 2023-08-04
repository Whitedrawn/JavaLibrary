package coursework.controller

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import com.zaxxer.hikari.HikariDataSource
import coursework.database.Database
import coursework.database.AUTHOR
import coursework.database.BOOK
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

object Controller {

    val path = "src/main/resources/library.sqlite"

    private val pcs = PropertyChangeSupport(this)

    var bookList: List<BOOK> = getBooks()
        get() = field
        private set(value) {
            val old = field
            field = value
            pcs.firePropertyChange("bookList", old, field)
        }

    var authorList: List<AUTHOR> = getAuthor()
        get() = field
        private set(value) {
            val old = field
            field = value
            pcs.firePropertyChange("authorList", old, field)
        }

    private fun getBooks(): List<BOOK> {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        return sqlQueries.allBooks().executeAsList()
    }

    private fun getSqlDriver(path: String ): SqlDriver {
        val ds = HikariDataSource()
        ds.jdbcUrl = "jdbc:sqlite:" + path
        ds.driverClassName = "org.sqlite.JDBC"
        ds.username = ""
        ds.password = ""
        return ds.asJdbcDriver()
    }

    fun add_Book(Title: String, Publisher: String, Year_Of_Publication: Long, Subject: String, Author: Long?=null) {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.insertBook(Title,Publisher,Year_Of_Publication, Subject, Author)
        bookList = getBooks()
    }

    private fun getAuthor(): List<AUTHOR> {
        val database = Database(getSqlDriver(path))
        val authorQueries = database.cWQueries
        return authorQueries.allAuthors().executeAsList()
    }


    fun addAuthor(First_Name: String, Last_Name: String) {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.insertAuthor(First_Name, Last_Name)
        authorList = getAuthor()
    }


    fun addPropertyChangeListener(pcl: PropertyChangeListener?) {
        pcs.addPropertyChangeListener(pcl)
    }

    fun removePropertyChangeListener(pcl: PropertyChangeListener?) {
        pcs.removePropertyChangeListener(pcl)
    }



}

fun main() {
    Controller.bookList.forEach { it ->
        println(it)
    }
    Controller.add_Book("asdasdasd", "kek", 3333, "asdwe", null)
    Controller.bookList.forEach { it ->
        println(it)
    }
}