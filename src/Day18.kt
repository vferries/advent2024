import model.GridPos

object Day18 : Day() {
    override fun p1(input: List<String>): String = shortestPath(0..70, input.take(1024)).toString()

    override fun p2(input: List<String>): String = firstBlockingByte(0..70, input)

    fun shortestPath(memorySpace: IntRange, bytes: List<String>): Int {
        val obstacles = bytes.map {
            val (x, y) = it.split(",").map(String::toInt)
            GridPos(y, x)
        }
        val end = GridPos(memorySpace.last, memorySpace.last)
        val toVisit = mutableListOf(GridPos(0, 0) to 0)
        val visited = mutableSetOf<GridPos>()
        while (toVisit.isNotEmpty()) {
            val (next, steps) = toVisit.removeFirst()
            if (next == end) {
                return steps
            }
            if (visited.contains(next)) {
                continue
            }
            visited += next
            toVisit.addAll(
                next.neighbors()
                    .filter { it.row in memorySpace && it.col in memorySpace }
                    .filter { it !in obstacles }
                    .map { it to steps + 1 }
            )
        }
        throw NoSuchElementException("No shortest path found")
    }

    fun firstBlockingByte(memorySpace: IntRange, bytes: List<String>): String {
        var lastOk = 0
        var firstNok = bytes.size
        while (lastOk < firstNok - 1) {
            val middle = (lastOk + firstNok) / 2
            try {
                shortestPath(memorySpace, bytes.take(middle + 1))
                lastOk = middle
            } catch (nse: NoSuchElementException) {
                firstNok = middle
            }
        }
        return bytes[firstNok]
    }
}

fun main() {
    val input = readInput("Day18")
    Day18.part1(input).println()
    Day18.part2(input).println()
}
