class SearchEngine {

    fun register(page: WebPage) {
        TODO("Not yet implemented")
    }

    fun registerAll(pages: Iterable<WebPage>) {
        pages.forEach { register(it) }
    }

    fun search(word: String): WebPage {
        TODO("Not yet implemented")
    }
}
