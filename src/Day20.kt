import model.GridPos
import java.util.function.Predicate

object Day20 : Day() {
    override fun p1(input: List<String>): String {
        return cheats(2, input) { it >= 100 }.toString()
    }

    override fun p2(input: List<String>): String {
        return cheats(20, input) { it >= 100 }.toString()
    }

    fun cheats(picoSeconds: Int, input: List<String>, predicate: Predicate<Int>): Int {
        val shortestPath = extractShortestPaths(input)
        return countCheats(picoSeconds, shortestPath, predicate)
    }

    private fun countCheats(
        picoSeconds: Int,
        shortestPath: MutableMap<GridPos, Int>,
        predicate: Predicate<Int>
    ): Int {
        var total = 0
        val allPos = shortestPath.keys.toList()
        for (i in allPos.indices) {
            for (j in (i + 1)..allPos.lastIndex) {
                val start = allPos[i]
                val dest = allPos[j]
                val manhattan = start.manhattan(dest)
                if (manhattan <= picoSeconds) {
                    val gain = shortestPath.getValue(start) - shortestPath.getValue(dest) - manhattan
                    if (predicate.test(gain)) {
                        total++
                    }
                }
            }
        }
        return total
    }

    private fun extractPos(c: Char, input: List<String>): GridPos {
        val startingRow = input.indexOfFirst { it.contains(c) }
        val startingColumn = input[startingRow].indexOf(c)
        val startingPos = GridPos(startingRow, startingColumn)
        return startingPos
    }

    private fun extractShortestPaths(
        input: List<String>
    ): MutableMap<GridPos, Int> {
        val endingPos = extractPos('E', input)
        val toVisit = mutableListOf(endingPos to 0)
        val shortestPath = mutableMapOf<GridPos, Int>()
        while (toVisit.isNotEmpty()) {
            val (pos, steps) = toVisit.removeFirst()
            if (shortestPath.contains(pos)) {
                continue
            }
            shortestPath += pos to steps
            toVisit += pos.neighbors()
                .filter { input[it.row][it.col] != '#' }
                .map { it to steps + 1 }
        }
        return shortestPath
    }
}

fun main() {
    val input = readInput("Day20")
    Day20.part1(input).println()
    Day20.part2(input).println()
}
