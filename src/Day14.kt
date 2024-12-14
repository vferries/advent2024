import model.Pos

object Day14 : Day() {
    override fun p1(input: List<String>): String {
        return safetyFactor(101, 103, input).toString()
    }

    fun safetyFactor(wide: Int, tall: Int, input: List<String>): Int {
        var robots = parseRobots(input)
        repeat(100) {
            robots = robots.map { it.move(wide, tall) }
        }
        val topLeft = robots.count { it.position.x < wide / 2 && it.position.y < tall / 2 }
        val topRight = robots.count { it.position.x > wide / 2 && it.position.y < tall / 2 }
        val bottomLeft = robots.count { it.position.x < wide / 2 && it.position.y > tall / 2 }
        val bottomRight = robots.count { it.position.x > wide / 2 && it.position.y > tall / 2 }

        return topLeft * topRight * bottomLeft * bottomRight
    }

    private fun parseRobots(input: List<String>): List<Robot> = input.mapIndexed { index, line ->
        val (px, py, vx, vy) = "p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)".toRegex()
            .find(line)!!.destructured.toList().map { it.toInt() }
        Robot(index, Pos(px, py), Pos(vx, vy))
    }

    override fun p2(input: List<String>): String {
        var robots = parseRobots(input)
        var count = 0
        while (robots.groupBy { it.position }.size != input.size) {
            robots = robots.map { it.move(101, 103) }
            count++
        }
        //printRobots(robots)
        return count.toString()
    }

    private fun printRobots(robots: List<Robot>) {
        for (row in 0 until 103) {
            for (col in 0 until 101) {
                print(robots.find { it.position == Pos(col, row) }?.let { "#" } ?: " ")
            }
            println("")
        }
    }
}

data class Robot(val id: Int, val position: Pos, val velocity: Pos) {
    fun move(wide: Int, tall: Int): Robot {
        val newX = (position.x + velocity.x + wide) % wide
        val newY = (position.y + velocity.y + tall) % tall
        return copy(position = Pos(newX, newY))
    }
}

fun main() {
    val input = readInput("Day14")
    Day14.part1(input).println()
    Day14.part2(input).println()
}
