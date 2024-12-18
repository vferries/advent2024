import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day18Test : ShouldSpec({
    val sample = """5,4
4,2
4,5
3,0
2,1
6,3
2,4
1,5
0,6
3,3
2,6
5,1
1,2
5,5
2,5
6,5
1,4
0,4
6,4
1,1
6,1
1,0
0,5
1,6
2,0""".lines()

    should("verify part1 on sample list") {
        Day18.shortestPath(0..6, sample.take(12)) shouldBe 22
    }

    should("verify part2 on sample list") {
        Day18.firstBlockingByte(0..6, sample) shouldBe "6,1"
    }
})
