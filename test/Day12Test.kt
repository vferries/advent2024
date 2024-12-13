import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day12Test : ShouldSpec({
    val sample1 = """OOOOO
OXOXO
OOOOO
OXOXO
OOOOO""".lines()
    val sample2 = """RRRRIICCFF
RRRRIICCCF
VVRRRCCFFF
VVRCCCJFFF
VVVVCJJCFE
VVIVCCJJEE
VVIIICJJEE
MIIIIIJJEE
MIIISIJEEE
MMMISSJEEE""".lines()

    should("verify part1 on first sample list") {
        Day12.part1(sample1) shouldBe "772"
    }

    should("verify part1 on second sample list") {
        Day12.part1(sample2) shouldBe "1930"
    }

    should("verify part2 on first sample list") {
        Day12.part2("""AAAA
BBCD
BBCC
EEEC""".lines()) shouldBe "80"
    }

    should("verify part2 on second sample list") {
        Day12.part2(sample1) shouldBe "436"
    }
})
