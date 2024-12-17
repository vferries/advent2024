import model.GridPos
import model.Orientation.EAST
import java.util.*

typealias ReindeerPos = GuardPos

object Day16 : Day() {
    override fun p1(input: List<String>): String {
        val starting = extractPos('S', input)
        val ending = extractPos('E', input)

        val visited = mutableSetOf<ReindeerPos>()
        val toVisit = PriorityQueue<Pair<Int, ReindeerPos>>(compareBy { it.first })
        toVisit.add(0 to ReindeerPos(starting, EAST))
        while (toVisit.isNotEmpty()) {
            val (cost, pos) = toVisit.poll()
            // If at the end, return
            if (pos.position == ending) {
                return cost.toString()
            }
            if (visited.contains(pos)) {
                continue
            } else {
                visited.add(pos)
            }
            // Else pile up neighbors
            toVisit.add(cost + 1000 to pos.copy(orientation = pos.orientation.right()))
            toVisit.add(cost + 1000 to pos.copy(orientation = pos.orientation.left()))
            val forward = pos.position.forward(pos.orientation)
            if (input[forward.row][forward.col] != '#') {
                toVisit.add(cost + 1 to pos.copy(position = forward))
            }
        }
        return "Not found"
    }

    private fun extractPos(c: Char, input: List<String>): GridPos {
        val startingRow = input.indexOfFirst { it.contains(c) }
        val startingColumn = input[startingRow].indexOf(c)
        val startingPos = GridPos(startingRow, startingColumn)
        return startingPos
    }

    override fun p2(input: List<String>): String {
        val starting = extractPos('S', input)
        val ending = extractPos('E', input)

        val bestScore = mutableMapOf<ReindeerPos, Int>()
        val previousPositions = mutableMapOf<ReindeerPos, Set<ReindeerPos>>()
        val toVisit = PriorityQueue<Triple<Int, ReindeerPos, ReindeerPos?>>(compareBy { it.first })
        toVisit.add(Triple(0, ReindeerPos(starting, EAST), null))
        val bestSolutionsPrevious = mutableSetOf<ReindeerPos>()
        var bestSolutionScore = Integer.MAX_VALUE
        while (toVisit.isNotEmpty()) {
            val (cost, pos, previous) = toVisit.poll()
            if (cost > bestSolutionScore) {
                break
            }
            val best = bestScore.getOrElse(pos) { Integer.MAX_VALUE }
            if (cost < best) {
                bestScore[pos] = cost
                previousPositions[pos] = if (previous != null) {
                    setOf(previous)
                } else {
                    setOf()
                }
            } else if (cost == best) {
                if (previous != null) {
                    previousPositions[pos] = previousPositions.getValue(pos) + previous
                }
                continue
            } else {
                continue
            }
            // If at the end, return
            if (pos.position == ending) {
                bestSolutionScore = cost
                println("Solution found")
                bestSolutionsPrevious.add(previous!!)
            }
            // Else pile up neighbors
            val left = pos.copy(orientation = pos.orientation.right())
            val right = pos.copy(orientation = pos.orientation.right())
            toVisit.add(Triple(cost + 1000, right, pos))
            toVisit.add(Triple(cost + 1000, left, pos))
            val forward = pos.position.forward(pos.orientation)
            if (input[forward.row][forward.col] != '#') {
                toVisit.add(
                    Triple(
                        cost + 1,
                        pos.copy(position = forward),
                        pos
                    )
                )
            }
        }

        val allReinderPos = mutableSetOf<ReindeerPos>()
        val next = bestSolutionsPrevious.toMutableList()

        while (next.isNotEmpty()) {
            val first = next.removeFirst()
            allReinderPos.add(first)
            next.addAll(previousPositions.getOrElse(first) { listOf() })
        }
        return (allReinderPos.map { it.position }.toSet().size + 1).toString()
    }
}

fun main() {
    val input = readInput("Day16")
    Day16.part1(input).println()
    Day16.part2(input).println()
}
