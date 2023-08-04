package coursework.model

import coursework.database.BOOK
import coursework.database.Database
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import com.zaxxer.hikari.HikariDataSource
import coursework.database.AUTHOR
import coursework.database.Book_by_author


object DDBB {


    val path = "src/main/resources/library.sqlite"

    fun booksByAuthor( id: Long): List<Book_by_author> {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        return sqlQueries.book_by_author(id).executeAsList()
    }


    fun getBook(): List<BOOK> {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        return sqlQueries.allBooks().executeAsList()
    }

    fun add_Book(Title: String, Publisher: String, Year_Of_Publication: Long, Subject: String, Author: Long?=null) {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.insertBook(Title, Publisher, Year_Of_Publication, Subject, Author)
    }


    private fun getSqlDriver(path: String ): SqlDriver {
        val ds = HikariDataSource()
        ds.jdbcUrl = "jdbc:sqlite:" + path
        ds.driverClassName = "org.sqlite.JDBC"
        ds.username = ""
        ds.password = ""
        return ds.asJdbcDriver()
    }

    fun addAuthor(First_Name: String, Last_Name: String) {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        sqlQueries.insertAuthor(First_Name, Last_Name)
    }
    fun getAuthor(): List<AUTHOR> {
        val database = Database(getSqlDriver(path))
        val authorQueries = database.cWQueries
        return authorQueries.allAuthors().executeAsList()
    }

}


// this is for testing.
//fun main() {
//    val lectures = DDBB.getLecturers()
//    for (lecture in lectures) {
//        println(lecture)
//    }
//    println(lectures)
//
//    val faculties = DDBB.getFaculties()
//    for (faculty in faculties) {
//        println(faculty)
//
//    }
//}

