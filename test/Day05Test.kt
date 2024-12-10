import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day05Test : ShouldSpec({
    val sample = """47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47""".lines()

    should("verify part1 on sample list") {
        Day05.part1(sample) shouldBe "143"
    }

    should("verify part2 on sample list") {
        Day05.part2(sample) shouldBe "123"
    }
})
