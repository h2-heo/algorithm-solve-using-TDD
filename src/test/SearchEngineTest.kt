import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SearchEngineTest {

    @Test
    fun `search() should return index of highest score page`() {
        val pages = listOf(
                WebPage(
                        url = "https://a.com",
                        links = listOf(),
                        words = listOf(),
                ),
                WebPage(
                        url = "https://b.com",
                        links = listOf(),
                        words = listOf("hi"),
                ),
                WebPage(
                        url = "https://c.com",
                        links = listOf(),
                        words = listOf(),
                ),
        )
        val word = "hi"

        val searchEngine = SearchEngine()
        searchEngine.registerAll(pages)

        assertEquals(pages[1], searchEngine.search(word))
    }

    @Test
    fun `search() should return minimum index of multiple highest score pages`() {
        val pages = listOf(
                WebPage(
                        url = "https://a.com",
                        links = listOf(),
                        words = listOf("hi"),
                ),
                WebPage(
                        url = "https://b.com",
                        links = listOf(),
                        words = listOf(),
                ),
                WebPage(
                        url = "https://c.com",
                        links = listOf(),
                        words = listOf("hi"),
                ),
        )
        val word = "hi"

        val searchEngine = SearchEngine()
        searchEngine.registerAll(pages)

        assertEquals(pages[0], searchEngine.search(word))
    }
}