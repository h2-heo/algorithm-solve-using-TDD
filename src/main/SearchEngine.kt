class SearchEngine {

    private val pages = arrayListOf<WebPage>()

    fun register(page: WebPage) {
        pages.add(page)
    }

    fun registerAll(pages: Iterable<WebPage>) {
        pages.forEach { register(it) }
    }

    fun search(word: String): WebPage? {
        return pages.maxBy { getMatchingScore(it, word) }
    }

    private fun getMatchingScore(page: WebPage, word: String): Double {
        return PageMatcher()
                .apply { addAll(pages) }
                .getMatchingScore(page, word)
    }
}
