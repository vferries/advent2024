import kotlin.math.abs

object Day02 : Day() {
    override fun p1(input: List<String>): Int =
        input.count { line ->
            val numbers = line.split(" +".toRegex()).map(String::toInt)
            isSafe(numbers)
        }

    override fun p2(input: List<String>): Int =
        input.count { line ->
            val numbers = line.split(" +".toRegex()).map(String::toInt)
            numbers.indices.map { index -> numbers.take(index) + numbers.drop(index + 1) }.any { isSafe(it) }
        }

    private fun isSafe(numbers: List<Int>) =
        (numbers == numbers.sorted() || numbers == numbers.sortedDescending()) &&
                numbers.windowed(2).all { abs(it.first() - it.last()) in (1..3) }
}

fun main() {
    val input = readInput("Day02")
    Day02.part1(input).println()
    Day02.part2(input).println()
}
