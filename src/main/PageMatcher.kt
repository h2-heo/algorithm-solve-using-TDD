class PageMatcher {

    private val pages = arrayListOf<WebPage>()

    fun add(page: WebPage) {
        pages.add(page)
    }

    fun addAll(pages: Iterable<WebPage>): PageMatcher {
        this.pages.addAll(pages)
        return this
    }

    fun getMatchingScore(page: WebPage, word: String): Double {
        return getBaseScore(page, word) + getLinkScore(page, word)
    }

    fun getBaseScore(page: WebPage, word: String): Double {
        return page.words.count { it.equals(word, ignoreCase = true) }.toDouble()
    }

    fun getLinkScore(page: WebPage, word: String): Double {
        val linkedPages = getLinkedPages(page)
        return linkedPages.sumByDouble { getBaseScore(it, word) / it.links.size }
    }

    private fun getLinkedPages(page: WebPage): List<WebPage> {
        return pages.filter { it.links.contains(page.url) }
    }
}
