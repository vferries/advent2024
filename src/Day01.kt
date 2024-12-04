import kotlin.math.abs

object Day01: Day() {
    override fun p1(input: List<String>): String {
        val (first, last) = extractLists(input)
        return first.zip(last).sumOf { abs(it.first - it.second) }.toString()
    }

    override fun p2(input: List<String>): String {
        val (first, last) = extractLists(input)
        return first.sumOf { f -> f * last.count { it == f } }.toString()
    }

    private fun extractLists(input: List<String>): Pair<List<Int>, List<Int>> {
        val lines = input.map { it.split("\\s+".toRegex()) }
        val first = lines.map { it.first().toInt() }.sorted()
        val last = lines.map { it.last().toInt() }.sorted()
        return Pair(first, last)
    }
}

fun main() {
    val input = readInput("Day01")
    Day01.part1(input).println()
    Day01.part2(input).println()
}
