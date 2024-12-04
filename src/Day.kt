import kotlin.time.measureTime

abstract class Day {
    fun part1(input: List<String>): String {
        var result: String
        val time = measureTime {
            result = p1(input)
        }
        println("Spent $time in part 1")
        return result
    }

    fun part2(input: List<String>): String {
        var result: String
        val time = measureTime {
            result = p2(input)
        }
        println("Spent $time in part 2")
        return result
    }

    abstract fun p1(input: List<String>): String
    abstract fun p2(input: List<String>): String
}
