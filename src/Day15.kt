import Occupant.BOX
import Occupant.EMPTY
import model.GridPos

enum class Occupant {
    EMPTY, BOX
}

object Day15 : Day() {
    override fun p1(input: List<String>): String {
        val (initPos, warehouse) = extractWareHouse(input)
        var current = initPos
        extractDirections(input).forEach { d ->
            val move = when (d) {
                '^' -> GridPos(-1, 0)
                'v' -> GridPos(1, 0)
                '>' -> GridPos(0, 1)
                '<' -> GridPos(0, -1)
                else -> {
                    throw UnsupportedOperationException("direction $d")
                }
            }
            val firstMove = current + move
            var next = firstMove
            while (next in warehouse.keys && warehouse[next] != EMPTY) {
                next += move
            }
            if (warehouse[next] == EMPTY) {
                current = firstMove
                if (next != firstMove) {
                    warehouse[next] = BOX
                    warehouse[firstMove] = EMPTY
                }
            }
        }
        return warehouse
            .filter { (_, occupant) -> occupant == BOX }
            .map { it.key }
            .sumOf { it.row * 100 + it.col }
            .toString()
    }

    private fun extractWareHouse(input: List<String>): Pair<GridPos, MutableMap<GridPos, Occupant>> {
        val warehouse = input.takeWhile { it.isNotBlank() }
        val warehouseMap = mutableMapOf<GridPos, Occupant>()
        lateinit var initPos: GridPos
        for (row in warehouse.indices) {
            for (col in warehouse[row].indices) {
                val pos = GridPos(row, col)
                when (warehouse[row][col]) {
                    '@' -> {
                        initPos = pos
                        warehouseMap[pos] = EMPTY
                    }

                    '.' -> {
                        warehouseMap[pos] = EMPTY
                    }

                    'O' -> {
                        warehouseMap[pos] = BOX
                    }

                    else -> {

                    }
                }
            }
        }
        return initPos to warehouseMap
    }

    private fun extractWideWareHouse(input: List<String>): Triple<GridPos, Set<GridPos>, MutableSet<GridPos>> {
        val warehouse = input.takeWhile { it.isNotBlank() }
        val emptyCells = mutableSetOf<GridPos>()
        val boxes = mutableSetOf<GridPos>()
        lateinit var initPos: GridPos
        for (row in warehouse.indices) {
            for (col in warehouse[row].indices) {
                val left = GridPos(row, col * 2)
                val right = GridPos(row, col * 2 + 1)
                when (warehouse[row][col]) {
                    '@' -> {
                        initPos = left
                        emptyCells += left
                        emptyCells += right
                    }

                    '.' -> {
                        emptyCells += left
                        emptyCells += right
                    }

                    'O' -> {
                        emptyCells += left
                        emptyCells += right
                        boxes += left
                    }

                    else -> {

                    }
                }
            }
        }
        return Triple(initPos, emptyCells, boxes)
    }

    override fun p2(input: List<String>): String {
        val (initPos, emptyCells, boxes) = extractWideWareHouse(input)
        var current = initPos
        extractDirections(input).forEach { d ->
            val move = when (d) {
                '^' -> GridPos(-1, 0)
                'v' -> GridPos(1, 0)
                '>' -> GridPos(0, 1)
                '<' -> GridPos(0, -1)
                else -> {
                    throw UnsupportedOperationException("direction $d")
                }
            }
            val firstMove = current + move
            if (firstMove in emptyCells && moveIfPossible(listOf(firstMove), setOf(), move, emptyCells, boxes)) {
                current = firstMove
            }
        }
        return boxes
            .sumOf { it.row * 100 + it.col }
            .toString()
    }

    private fun moveIfPossible(
        moving: List<GridPos>,
        collisions: Set<GridPos>,
        move: GridPos,
        emptyCells: Set<GridPos>,
        boxes: MutableSet<GridPos>
    ): Boolean {
        if (!moving.all { it in emptyCells }) {
            return false
        }
        val colliding = boxes
            .filter { it !in collisions }
            .filter {
                it in moving || it + GridPos(0, 1) in moving
            }.toSet()
        if (colliding.isEmpty()) {
            return true
        }
        // recurse on collisions
        val nextMoving = colliding.flatMap { listOf(it + move, it + move + GridPos(0, 1)) }
        return if (moveIfPossible(nextMoving, collisions + colliding, move, emptyCells, boxes)) {
            // move all directly colliding boxes
            boxes.removeAll(colliding.toSet())
            boxes.addAll(colliding.map { it + move })
            true
        } else {
            false
        }
    }

    private fun extractDirections(input: List<String>) = input.takeLastWhile { it.isNotBlank() }.joinToString("")
}

fun main() {
    val input = readInput("Day15")
    Day15.part1(input).println()
    Day15.part2(input).println()
}
