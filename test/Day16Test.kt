import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day16Test : ShouldSpec({
    val sample1 = """###############
#.......#....E#
#.#.###.#.###.#
#.....#.#...#.#
#.###.#####.#.#
#.#.#.......#.#
#.#.#####.###.#
#...........#.#
###.#.#####.#.#
#...#.....#.#.#
#.#.#.###.#.#.#
#.....#...#.#.#
#.###.#.#.#.#.#
#S..#.....#...#
###############""".lines()

    val sample2 = """#################
#...#...#...#..E#
#.#.#.#.#.#.#.#.#
#.#.#.#...#...#.#
#.#.#.#.###.#.#.#
#...#.#.#.....#.#
#.#.#.#.#.#####.#
#.#...#.#.#.....#
#.#.#####.#.###.#
#.#.#.......#...#
#.#.###.#####.###
#.#.#...#.....#.#
#.#.#.#####.###.#
#.#.#.........#.#
#.#.#.#########.#
#S#.............#
#################""".lines()

    should("verify part1 on first sample list") {
        Day16.part1(sample1) shouldBe "7036"
    }
    should("verify part1 on second sample list") {
        Day16.part1(sample2) shouldBe "11048"
    }

    should("verify part2 on first sample list") {
        Day16.part2(sample1) shouldBe "45"
    }
    should("verify part2 on second sample list") {
        Day16.part2(sample2) shouldBe "64"
    }

})
