package coursework.sorting

object Buble {
  def sort(my_array: Array[BOOK]) = {
    var n: Int = my_array.size;
    var ticks = 0;
    // Q: n=min p : 0 <= p <= N and p<N-2 -> A[p]>A[p+1]: p
    // quota : n >= 0
    // I : \\forall i : n <= i < size: A[i]<A[i+1]
    do {
      var newn = 0
      // I2: newn > 0 -> \forall i: newn < i <size : A[i-1]<A[i]
      for (i <- 1 until n) {
        ticks += 1
        if (my_array(i - 1).getAGE > my_array(i).getyear_Of_Publication) {
          val tmp = my_array(i - 1)
          my_array(i - 1) = my_array(i)
          my_array(i) = tmp
          newn = i
        }
      }
      n = newn
    } while (n > 1)
    //        for (i in my_array)     println(i.AGE)
    //        println("----");


  }

}
