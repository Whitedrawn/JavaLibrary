package coursework

object Interpreter extends App {
  val banner = List("0 - Exit", "1 - Load Database", "2 - Reset Database", "3 - Show faculties", "4 - Show lecturers")

  var command: Int = io.StdIn.readInt() // var, because it can be overwritten. while (command != 0) {
    command match {
      case 1 => for (i <- List(DDBB.INSTANCE.getLecturers())) println(i)
      case 2 => println("Rest Database")
      case 3 => println("Show faculties")
      case 4 => println("Show lecturers")
      case _ => println("Unkown command")
    }
    command = io.StdIn.readInt()

}
