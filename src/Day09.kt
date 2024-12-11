object Day09 : Day() {
    override fun p1(input: List<String>): String {
        val line = input[0]
        var lineIndex = 0
        var position = 0
        var last =
            if (line.lastIndex % 2 == 0) {
                line.lastIndex
            } else {
                line.lastIndex - 1
            }
        var lastToMove = FileBlock(last / 2, line[last].digitToInt())
        var checksum = 0L
        while (lineIndex < last) {
            val blocks = line[lineIndex].digitToInt()
            when {
                lineIndex % 2 == 0 -> {
                    val id = lineIndex / 2
                    repeat(blocks) {
                        checksum += id * position
                        position++
                    }
                }

                else -> {
                    repeat(blocks) {
                        if (lastToMove.size == 0) {
                            last -= 2
                            if (last < lineIndex) {
                                return checksum.toString()
                            }
                            lastToMove = FileBlock(last / 2, line[last].digitToInt())
                        }
                        lastToMove = lastToMove.copy(size = lastToMove.size - 1)
                        checksum += lastToMove.id * position
                        position++
                    }
                }
            }
            lineIndex++
        }
        repeat(lastToMove.size) {
            checksum += lastToMove.id * position
            position++
        }
        return checksum.toString()
    }

    override fun p2(input: List<String>): String {
        val line = input[0]
        val digits = line.map(Char::digitToInt)
        val totalSize = digits.sum()
        val disk = Array(totalSize) { -1 }
        var position = 0
        val sizes = mutableMapOf<Int, Int>()
        val firstIndexes = mutableMapOf<Int, Int>()
        // Fill disk and store important info
        digits.forEachIndexed { index, size ->
            if (index % 2 == 0) {
                sizes[index / 2] = size
                firstIndexes[index / 2] = position
                repeat(size) {
                    disk[position] = index / 2
                    position++
                }
            } else {
                position += size
            }
        }
        sizes.keys.sortedDescending().forEach { id ->
            val size = sizes.getValue(id)
            val found = findSuitablePlace(disk, size)
            if (found != null && found < firstIndexes.getValue(id)) {
                disk.fill(-1, firstIndexes.getValue(id), firstIndexes.getValue(id) + size)
                disk.fill(id, found, found + size)
            }
        }
        return disk.checksum()
    }

    private fun findSuitablePlace(disk: Array<Int>, size: Int): Int? {
        var currentIndex = 0
        var emptySpaces = 0
        while (currentIndex < disk.size) {
            when (disk[currentIndex]) {
                -1 -> {
                    emptySpaces++
                    if (emptySpaces == size) {
                        return currentIndex - size + 1
                    }
                }

                else -> {
                    emptySpaces = 0
                }
            }
            currentIndex++
        }
        return null
    }
}

private fun Array<Int>.checksum(): String = foldIndexed(0L) { index, acc, id ->
    when (id) {
        -1 -> {
            acc
        }

        else -> acc + id * index
    }
}.toString()

interface Block {
    val size: Int
}

data class FileBlock(val id: Int, override val size: Int) : Block

fun main() {
    val input = readInput("Day09")
    Day09.part1(input).println()
    Day09.part2(input).println()
}
