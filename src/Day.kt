import kotlin.time.measureTime

abstract class Day {
    fun part1(input: List<String>): Int {
        var result: Int
        val time = measureTime {
            result = p1(input)
        }
        println("Spent $time in part 1")
        return result
    }

    fun part2(input: List<String>): Int {
        var result: Int
        val time = measureTime {
            result = p2(input)
        }
        println("Spent $time in part 2")
        return result
    }

    abstract fun p1(input: List<String>): Int
    abstract fun p2(input: List<String>): Int
}
