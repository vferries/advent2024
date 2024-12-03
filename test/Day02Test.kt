import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Test {
    @Test
    fun `Should verify part1 on sample list`() {
        assertEquals(
            2, Day02.part1(
                """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9""".lines()
            )
        )
    }
    @Test
    fun `Should verify part2 on sample list`() {
        assertEquals(
            4, Day02.part2(
                """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9""".lines()
            )
        )
    }
}