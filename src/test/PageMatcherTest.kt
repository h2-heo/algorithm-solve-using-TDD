import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal abstract class PageMatcherTest {

    abstract fun createPageMatcher(): PageMatcher

    @Test
    fun `base score should be number of finding words (ignore case)`() {
        val page = WebPage(
                url = "https://a.com",
                links = listOf(),
                words = listOf("hi", "hi", "hI", "Hi", "bye", "HI", "whi", "bYe"),
        )
        val word = "hi"
        val baseScore = 5.0

        val matcher = createPageMatcher().apply { add(page) }
        assertEquals(baseScore, matcher.getBaseScore(page, word))
    }

    @Test
    fun `link score should be sum of (base score ÷ number of links) on other pages that linked to the page`() {
        val pages = listOf(
                WebPage(
                        url = "https://a.com",
                        links = listOf(),
                        words = listOf("hi"),
                ),
                WebPage(
                        url = "https://b.com",
                        links = listOf("https://a.com", "https://c.com"),
                        words = listOf("hi", "hi"),
                ),
                WebPage(
                        url = "https://c.com",
                        links = listOf("https://a.com"),
                        words = listOf("hi", "hi", "hi"),
                ),
        )
        val page = pages[0]
        val word = "hi"
        val linkScore = (2.0 / 2) + (3.0 / 1)

        val matcher = createPageMatcher().apply { addAll(pages) }
        assertEquals(linkScore, matcher.getLinkScore(page, word))
    }

    @Test
    fun `matching score should be sum of base score and link score`() {
        val pages = listOf(
                WebPage(
                        url = "https://a.com",
                        links = listOf(),
                        words = listOf("hi"),
                ),
                WebPage(
                        url = "https://b.com",
                        links = listOf("https://a.com", "https://c.com"),
                        words = listOf("hi", "hi"),
                ),
                WebPage(
                        url = "https://c.com",
                        links = listOf("https://a.com"),
                        words = listOf("hi", "hi", "hi"),
                ),
        )
        val page = pages[0]
        val word = "hi"
        val baseScore = 1.0
        val linkScore = (2.0 / 2) + (3.0 / 1)
        val matchingScore = baseScore + linkScore

        val matcher = createPageMatcher().apply { addAll(pages) }
        assertEquals(matchingScore, matcher.getMatchingScore(page, word))
    }
}