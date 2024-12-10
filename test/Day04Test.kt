import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day04Test : ShouldSpec({
    should("verify part1 on sample list") {
        Day04.part1(
            """MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX""".lines()
        ) shouldBe "18"
    }
    should("verify part2 on sample list") {
        Day04.part2(
            """MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX""".lines()
        ) shouldBe "9"
    }
})