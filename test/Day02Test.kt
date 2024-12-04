import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day02Test {
    @Test
    fun `Should verify part1 on sample list`() {
        Day02.part1(
            """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9""".lines()
        ) shouldBe 2
    }

    @Test
    fun `Should verify part2 on sample list`() {
        Day02.part2(
            """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9""".lines()
        ) shouldBe 4
    }
}