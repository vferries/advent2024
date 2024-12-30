private typealias PinHeights = List<Int>

object Day25 : Day() {
    override fun p1(input: List<String>): String {
        val (locks, keys) = parse(input)
        return locks.flatMap { lock -> keys.map { key -> lock to key } }
            .count { (lock, key) -> lock.indices.all { i -> lock[i] + key[i] <= 5 } }.toString()
    }

    private fun parse(input: List<String>): Pair<List<PinHeights>, List<PinHeights>> {
        val locks = mutableListOf<PinHeights>()
        val keys = mutableListOf<PinHeights>()

        val blocks = input.joinToString("\n").split("\n\n")
        for (block in blocks) {
            val lines = block.lines()
            val columns = lines[0].indices.map { column -> lines.map { it[column] }.joinToString("") }
            val heights = columns.map { it.count { c -> c == '#' } - 1 }
            if (block[0] == '#') {
                locks += heights
            } else {
                keys += heights
            }
        }

        return locks to keys
    }

    override fun p2(input: List<String>): String {
        return ""
    }
}

fun main() {
    val input = readInput("Day25")
    Day25.part1(input).println()
    Day25.part2(input).println()
}
