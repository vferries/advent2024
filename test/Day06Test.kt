import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day06Test : ShouldSpec({
    val sample = """....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...""".lines()

    should("verify part1 on sample list") {
        Day06.part1(sample) shouldBe "41"
    }

    should("verify part2 on sample list") {
        Day06.part2(sample) shouldBe "6"
    }

    should("verify part2 on real input") {
        Day06.part2(readInput("Day06")) shouldBe "1933"
    }
})
