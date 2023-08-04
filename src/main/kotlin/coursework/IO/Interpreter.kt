package coursework.IO

import coursework.database.AUTHOR
import coursework.database.BOOK
import coursework.database.Book_by_author
import coursework.model.DDBB
import coursework.sorting.Bubble
import coursework.sorting.QuickSort

object Interpreter {

    fun BOOK.toRow(): String =
        "${this.id}\t${this.TITLE}" +
        "\t${this.PUBLISHER_NAME}\t${this.YEAR_OF_PUBLICATION}\t${this.SUBJECT}\t${this.AUTHOR_ID}"

    fun AUTHOR.toRow(): String = "${this.id}\t${this.FIRST_NAME}\t${this.LAST_NAME}"
    fun Book_by_author.toRow(): String = "${this.TITLE}\t${this.PUBLISHER_NAME}\t${this.YEAR_OF_PUBLICATION}\t${this.SUBJECT}"


    fun main() {

        Utilities.help()
        var code = 1 // Hack
        do {
            try {
                code = Utilities.prompt_Int()
                when (code) {
                    1 -> DDBB.getBook().forEach { lec ->
                        println(lec.toRow())
                    }

                    2 -> DDBB.getAuthor().forEach { author ->
                        println(author.toRow())
                    }

                    3 -> {
                        val first_name = Utilities.prompt_String("\t First Name : ")
                        val last_name = Utilities.prompt_String("\t Last Name : ")
                        DDBB.addAuthor(first_name, last_name)
                    }

                    4 -> {
                        val title = Utilities.prompt_String("\t Title : ")
                        val publisher_name = Utilities.prompt_String("\t Publisher : ")
                        val year_of_publication = Utilities.prompt_Int("\t Year Of Publication : ").toLong()
                        val subject = Utilities.prompt_String("\t Subject : ")
                        DDBB.add_Book(title, publisher_name ,year_of_publication, subject)
                    }

                    5 -> {
                        val scrambled = ArrayList(DDBB.getBook())
                        val choice = Utilities.prompt_Int("\tBubble(1),MergeSort(2),QuickSort(3) : ")
                        var n = 0
                        when (choice) {
                            1 -> {
                                n = Bubble.sort(scrambled)
                                scrambled.forEach { lec ->
                                    println(lec.toRow())
                                }
                            }

//                            2 -> {
//                                val pair = MergeSort.sort(scrambled)
//                                n = pair.second
//                                pair.first.forEach { lec ->
//                                    println(lec.toRow())
//                                }
//                            }

                            3 -> {
                                n = QuickSort.sort(scrambled)
                                scrambled.forEach { lec ->
                                    println(lec.toRow())
                                }
                            }
                        }
                        println("--------------------")
                        println("Ticks ${n}")
                    }

                    6 -> {
                        val id = Utilities.prompt_Int("\t Code : ")
                        DDBB.booksByAuthor(id.toLong()).forEach {
                            println(it.toRow())
                        }
                    }

                    0 -> println("Bye!")
                    10 -> Utilities.help()
                    else -> println("Uncorrect command")
                }
            } catch (e: Exception) {
                println("Uncorrect command")
            }
        } while (code != 0)
    }
}



