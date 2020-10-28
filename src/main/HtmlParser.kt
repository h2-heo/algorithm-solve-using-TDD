class HtmlParser {

    fun parse(html: String): WebPage {
        val (head, body) = getHeadBody(html)
        val url = getUrl(head)
        val links = getLinks(body)
        val words = getWords(body)
        return WebPage(url, links, words)
    }

    fun getHeadBody(html: String): Pair<String, String> {
        TODO("Not yet implemented")
    }

    fun getUrl(headHtml: String): String {
        TODO("Not yet implemented")
    }

    fun getLinks(bodyHtml: String): List<String> {
        TODO("Not yet implemented")
    }

    fun getWords(bodyHtml: String): List<String> {
        TODO("Not yet implemented")
    }
}
