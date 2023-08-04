package coursework.sorting

import coursework.database.BOOK

object QuickSort {
    var tick = 0
    fun sort(scrambled: ArrayList<BOOK>) : Int
    {
        // Inmmersion
        fun sort_g(scrambled: ArrayList<BOOK>, inf: Int, sup: Int): Int {
            if (sup - inf <= 1) {

            } else {
                val h = partition(scrambled, inf, sup)
                // Swap to reduce quota,
                val tmp = scrambled[inf]
                scrambled[inf] = scrambled[h - 1]
                scrambled[h - 1] = tmp
                //
                assert(((h - 1) - inf < sup - inf) && (sup - h < sup - inf))
                sort_g(scrambled, inf, h - 1)
                sort_g(scrambled, h, sup)
            }
            return tick
        }
        return sort_g(scrambled,0,scrambled.size)
    }



    // We cannot use slices of scrambled, since we modify the parameter itself.
    private fun partition(scrambled: ArrayList<BOOK>, inf: Int, sup: Int): Int {
        var h = inf + 1
        assert(sup - inf > 1)
        for (n in inf + 1 until sup) {
            tick+=1
            if (scrambled[n].YEAR_OF_PUBLICATION <= scrambled[inf].YEAR_OF_PUBLICATION) {
                val tmp = scrambled[h]
                scrambled[h] = scrambled[n]
                scrambled[n] = tmp
                h += 1
            }
        }
        assert(inf < h && inf <= sup)
        return h
    }
}

