import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day08Test : ShouldSpec({
    val sample = """............
........0...
.....0......
.......0....
....0.......
......A.....
............
............
........A...
.........A..
............
............""".lines()

    should("verify part1 on sample list") {
        Day08.part1(sample) shouldBe "14"
    }

    should("verify part2 on sample list") {
        Day08.part2(sample) shouldBe "34"
    }
})