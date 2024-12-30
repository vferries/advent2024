import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day25Test : ShouldSpec({
    val sample = """#####
.####
.####
.####
.#.#.
.#...
.....

#####
##.##
.#.##
...##
...#.
...#.
.....

.....
#....
#....
#...#
#.#.#
#.###
#####

.....
.....
#.#..
###..
###.#
###.#
#####

.....
.....
.....
#....
#.#..
#.#.#
#####""".lines()

    should("verify part1 on sample list") {
        Day25.part1(sample) shouldBe "3"
    }
})
