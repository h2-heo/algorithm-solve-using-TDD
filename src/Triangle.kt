class Triangle {
    companion object {
        fun create(triangularArray: Array<IntArray>): Triangle {
            if (!checkTriangularArray(triangularArray)) {
                throw IllegalArgumentException()
            }

            val instance = Triangle()
            instance.array = triangularArray
            return instance
        }

        fun zeros(n: Int): Triangle {
            val triangularArray = createTriangularArray(n)
            return create(triangularArray)
        }

        private fun checkTriangularArray(array: Array<IntArray>): Boolean {
            return array.mapIndexed { i, values -> values.size == i + 1 }.all { it }
        }

        private fun createTriangularArray(n: Int): Array<IntArray> {
            val list = arrayListOf<IntArray>()
            for (i in 0 until n) {
                list.add(IntArray(i + 1))
            }
            return list.toTypedArray()
        }
    }

    private lateinit var array: Array<IntArray>

    val top: Point = Point(0, 0)

    fun toArray(): IntArray {
        return array.flatMap { it.toList() }.toIntArray()
    }

    fun setValueAt(point: Point, value: Int) {
        array[point.row][point.col] = value
    }

    fun getValueAt(point: Point): Int {
        return array[point.row][point.col]
    }

    fun contains(point: Point): Boolean {
        if (point.row < 0 || point.row >= array.size) return false
        if (point.col < 0 || point.col >= array[point.row].size) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Triangle) {
            return false
        }
        return array.mapIndexed { i, row -> row.contentEquals(other.array[i]) }.all { it }
    }

    override fun hashCode(): Int {
        return array.hashCode()
    }
}
