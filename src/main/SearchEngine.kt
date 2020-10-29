class SearchEngine {

    private val pages = arrayListOf<WebPage>()
    private val matcher = FastPageMatcher()

    fun register(page: WebPage) {
        pages.add(page)
        matcher.add(page)
    }

    fun registerAll(pages: Iterable<WebPage>) {
        pages.forEach { register(it) }
    }

    fun search(word: String): WebPage? {
        return pages.maxBy { getMatchingScore(it, word) }
    }

    private fun getMatchingScore(page: WebPage, word: String): Double {
        return matcher.getMatchingScore(page, word)
    }
}
