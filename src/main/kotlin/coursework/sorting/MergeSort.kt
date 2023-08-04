package coursework.sorting

import coursework.database.BOOK

object MergeSort {

    var tick = 0
    fun sort(scrambled: ArrayList<BOOK>): Pair<ArrayList<BOOK>, Int> {
        tick = 0
        val res = ArrayList(scrambled)

        if (scrambled.size <= 1)
            return Pair(res, tick)
        else {
            val h = scrambled.size / 2
            val leftScrambled = ArrayList(scrambled.slice(0 until h))
            val rightScrambled = ArrayList(scrambled.slice(h until scrambled.size))

            val (leftSorted, _) = sort(leftScrambled)
            val (rightSorted, _)= sort(rightScrambled)

            merge_rec(leftSorted, rightSorted, res)

            return Pair(res, tick)
        }
    }

    fun merge_rec(
        leftSorted: ArrayList<BOOK>,
        rightSorted: ArrayList<BOOK>,
        res: ArrayList<BOOK>,
    ) {

        fun merge_rec_g(
            leftSorted: ArrayList<BOOK>,   l: Int,
            rightSorted: ArrayList<BOOK>,   r: Int,
            res: ArrayList<BOOK>,  t: Int,
        ) {
            if (!((l < leftSorted.size) || (r < rightSorted.size))) {
                assert((l == leftSorted.size) && (r == rightSorted.size))
            } else {
                assert(t < res.size)
                tick += 1
                if (l == leftSorted.size) {
                    res[t] = rightSorted[r]
                    merge_rec_g(leftSorted, l, rightSorted, r + 1, res, t + 1)
                } else if (r == rightSorted.size) {
                    res[t] = leftSorted[l]
                    merge_rec_g(leftSorted, l + 1, rightSorted, r, res, t + 1)
                } else {
                    if (leftSorted[l].YEAR_OF_PUBLICATION < rightSorted[r].YEAR_OF_PUBLICATION) {
                        res[t] = leftSorted[l]
                        merge_rec_g(leftSorted, l + 1, rightSorted, r, res, t + 1)
                    } else {
                        res[t] = rightSorted[r]
                        merge_rec_g(leftSorted, l, rightSorted, r + 1, res, t + 1)
                    }
                }
            }
        }

        // Inmmersion call.
        var (l, r, t) = Triple(0, 0, 0)
        merge_rec_g(leftSorted, l, rightSorted, r, res, t)  // not B
    }


}

//fun main() {
//    val (left, right, res) =Triple(
//            ArrayList(arrayListOf(-1,2,3)),
//            ArrayList(arrayListOf(1,12,13)),
//            ArrayList(arrayListOf(1,32,13,4,15,6)))
//    MergeSort.merge_R(left,right,res)
//    res.forEach { i ->
//        print("${i} ")
//    }
//}

//fun main() {
//    val left = ArrayList(arrayListOf(-1, 20, 3))
//    val (a,i) = MergeSort.sort(left)
//    println(i)
//    a.forEach({ println(it)}
//    )
//
//}
