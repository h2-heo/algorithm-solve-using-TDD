class SearchEngine {

    private val pages = arrayListOf<WebPage>()

    fun register(page: WebPage): SearchEngine {
        pages.add(page)
        return this
    }

    fun registerAll(pages: Iterable<WebPage>): SearchEngine {
        pages.forEach { register(it) }
        return this
    }

    fun search(word: String): WebPage? {
        return pages.maxBy { getMatchingScore(it, word) }
    }

    private fun getMatchingScore(page: WebPage, word: String): Double {
        return PageMatcher()
                .addAll(pages)
                .getMatchingScore(page, word)
    }
}
