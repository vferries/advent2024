import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day23Test : ShouldSpec({
    val sample = """kh-tc
qp-kh
de-cg
ka-co
yn-aq
qp-ub
cg-tb
vc-aq
tb-ka
wh-tc
yn-cg
kh-ub
ta-co
de-co
tc-td
tb-wq
wh-td
ta-ka
td-qp
aq-cg
wq-ub
ub-vc
de-ta
wq-aq
wq-vc
wh-yn
ka-de
kh-ta
co-tc
wh-qp
tb-vc
td-yn""".lines()

    should("verify part1 on sample list") {
        Day23.part1(sample) shouldBe "7"
    }

    should("verify part2 on sample list") {
        Day23.part2(sample) shouldBe "co,de,ka,ta"
    }
})
