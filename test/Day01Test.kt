import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Test {
    @Test
    fun `Should verify part1 on sample list`() {
        assertEquals(
            11, Day01.part1(
                """3   4
4   3
2   5
1   3
3   9
3   3""".lines()
            )
        )
    }
    @Test
    fun `Should verify part2 on sample list`() {
        assertEquals(
            31, Day01.part2(
                """3   4
4   3
2   5
1   3
3   9
3   3""".lines()
            )
        )
    }
}