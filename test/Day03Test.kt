import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day03Test {
    @Test
    fun `Should verify part1 on sample list`() {
        Day03.part1(
            """xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))""".lines()
        ) shouldBe 161
    }

    @Test
    fun `Should verify part2 on sample list`() {
        Day03.part2(
            """xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))""".lines()
        ) shouldBe 48
    }
}