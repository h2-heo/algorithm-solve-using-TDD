class QuadTreeElementCounter {

    fun visit(tree: QuadTree): IntArray {
        val counts = intArrayOf(0, 0)
        if (tree.isLeaf()) {
            counts[tree.value] += 1
        } else {
            tree.children.forEach {
                val childCounts = visit(it)
                counts[0] += childCounts[0]
                counts[1] += childCounts[1]
            }
        }

        return counts
    }
}
