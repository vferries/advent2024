import model.GridPos

typealias Keyboard = Map<Pair<Char, Char>, List<String>>

object Day21 : Day() {
    private val memo = mutableMapOf<Triple<String, Int, Boolean>, Long>()

    override fun p1(input: List<String>): String = computeShortest(input, 3).toString()

    override fun p2(input: List<String>): String = computeShortest(input, 26).toString()

    private fun toKeyboard(keypad: List<String>): Keyboard {
        val keyMap = keypad.flatMapIndexed { row, line -> line.mapIndexed { col, c -> c to GridPos(row, col) } }.toMap()
        val space = keyMap.getValue(' ')
        return keyMap.keys.flatMap { src ->
            keyMap.keys.map { dest ->
                val delta = keyMap.getValue(dest) - keyMap.getValue(src)
                val shortestPaths = allShortest(delta)
                    .filter { directions ->
                        // Ne passe pas par ' '
                        var pos = keyMap.getValue(src)
                        for (direction in directions) {
                            when (direction) {
                                '>' -> pos += GridPos(0, 1)
                                '<' -> pos += GridPos(0, -1)
                                '^' -> pos += GridPos(-1, 0)
                                'v' -> pos += GridPos(1, 0)
                            }
                            if (pos == space) {
                                return@filter false
                            }
                        }
                        true
                    }
                (src to dest) to shortestPaths
            }
        }.toMap().toMutableMap()
    }

    private fun allShortest(delta: GridPos): List<String> {
        if (delta == GridPos(0, 0)) {
            return listOf("A")
        }
        val shortests = mutableListOf<Pair<Char, GridPos>>()
        if (delta.row > 0) shortests.add('v' to GridPos(1, 0))
        if (delta.row < 0) shortests.add('^' to GridPos(-1, 0))
        if (delta.col > 0) shortests.add('>' to GridPos(0, 1))
        if (delta.col < 0) shortests.add('<' to GridPos(0, -1))
        return shortests.flatMap { (c, d) -> allShortest(delta - d).map { shortest -> c + shortest } }
    }

    private fun computeShortest(input: List<String>, directionalCount: Int): Long {
        val numericKeypad = listOf("789", "456", "123", " 0A")
        val numericShortestPaths = toKeyboard(numericKeypad)
        val directionalKeypad = listOf(" ^A", "<v>")
        val directionalShortestPaths = toKeyboard(directionalKeypad)

        return input.sumOf { code ->
            code.filter { it.isDigit() }.toLong() * toKeyboard(
                code,
                true,
                directionalCount,
                numericShortestPaths,
                directionalShortestPaths
            )
        }
    }

    private fun toKeyboard(
        code: String,
        numeric: Boolean,
        depth: Int,
        numericSp: Map<Pair<Char, Char>, List<String>>,
        directionalSp: Map<Pair<Char, Char>, List<String>>
    ): Long {
        if (depth == 0) {
            return code.length.toLong()
        }
        val triple = Triple(code, depth, numeric)
        if (!memo.contains(triple)) {
            memo[triple] = "A$code"
                .windowed(2)
                .sumOf { window ->
                    val keyboard = if (numeric) {
                        numericSp
                    } else {
                        directionalSp
                    }
                    keyboard.getValue(window[0] to window[1])
                        .minOf { toKeyboard(it, false, depth - 1, numericSp, directionalSp) }
                }

        }
        return memo.getValue(triple)
    }
}

fun main() {
    val input = readInput("Day21")
    Day21.part1(input).println()
    Day21.part2(input).println()
}
