internal class NaivePageMatcherTest: PageMatcherTest() {

    override fun createPageMatcher(): PageMatcher {
        return NaivePageMatcher()
    }
}
