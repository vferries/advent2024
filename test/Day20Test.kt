import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day20Test : ShouldSpec({
    val sample = """###############
#...#...#.....#
#.#.#.#.#.###.#
#S#...#.#.#...#
#######.#.#.###
#######.#.#...#
#######.#.###.#
###..E#...#...#
###.#######.###
#...###...#...#
#.#####.#.###.#
#.#...#.#.#...#
#.#.#.#.#.#.###
#...#...#...###
###############""".lines()

    should("verify part1 on sample list") {
        Day20.cheats(2, sample) { it == 2 } shouldBe 14
        Day20.cheats(2, sample) { it == 4 } shouldBe 14
        Day20.cheats(2, sample) { it == 6 } shouldBe 2
        Day20.cheats(2, sample) { it == 8 } shouldBe 4
        Day20.cheats(2, sample) { it == 10 } shouldBe 2
        Day20.cheats(2, sample) { it == 12 } shouldBe 3
        Day20.cheats(2, sample) { it == 20 } shouldBe 1
        Day20.cheats(2, sample) { it == 36 } shouldBe 1
        Day20.cheats(2, sample) { it == 38 } shouldBe 1
        Day20.cheats(2, sample) { it == 40 } shouldBe 1
        Day20.cheats(2, sample) { it == 64 } shouldBe 1
   }

    should("verify part2 on sample list") {
        Day20.cheats(20, sample) { it == 50 } shouldBe 32
        Day20.cheats(20, sample) { it == 52 } shouldBe 31
        Day20.cheats(20, sample) { it == 54 } shouldBe 29
        Day20.cheats(20, sample) { it == 56 } shouldBe 39
        Day20.cheats(20, sample) { it == 58 } shouldBe 25
        Day20.cheats(20, sample) { it == 60 } shouldBe 23
        Day20.cheats(20, sample) { it == 62 } shouldBe 20
        Day20.cheats(20, sample) { it == 64 } shouldBe 19
        Day20.cheats(20, sample) { it == 66 } shouldBe 12
        Day20.cheats(20, sample) { it == 68 } shouldBe 14
        Day20.cheats(20, sample) { it == 70 } shouldBe 12
        Day20.cheats(20, sample) { it == 72 } shouldBe 22
        Day20.cheats(20, sample) { it == 74 } shouldBe 4
        Day20.cheats(20, sample) { it == 76 } shouldBe 3
    }
})
