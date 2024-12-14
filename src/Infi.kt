object Infi : Day() {
    override fun p1(input: List<String>): String = allPos().sumOf { (x, y, z) ->
        cloudLevel(input, x, y, z)
    }.toString()

    private fun cloudLevel(input: List<String>, x: Int, y: Int, z: Int): Int {
        val stack = mutableListOf<Int>()
        var counter = 0
        val result: Int?
        while (true) {
            val instruction = input[counter]
            when {
                instruction.startsWith("push") -> {
                    val arg = instruction.substringAfter("push ")
                    val value = when (arg) {
                        "X" -> x
                        "Y" -> y
                        "Z" -> z
                        else -> arg.toInt()
                    }
                    stack.add(0, value)
                }

                instruction == "add" -> {
                    val a = stack.removeFirst()
                    val b = stack.removeFirst()
                    stack.add(0, a + b)
                }

                instruction.startsWith("jmpos") -> {
                    val offset = instruction.substringAfter("jmpos ").toInt()
                    val a = stack.removeFirst()
                    if (a >= 0) {
                        counter += offset
                    }
                }

                instruction == "ret" -> {
                    result = stack.removeFirst()
                    break
                }

                else -> {}
            }
            counter++
        }
        return result!!
    }

    private fun allPos(): List<Triple<Int, Int, Int>> {
        val allPos = mutableListOf<Triple<Int, Int, Int>>()
        for (x in 0 until 30) {
            for (y in 0 until 30) {
                for (z in 0 until 30) {
                    allPos += Triple(x, y, z)
                }
            }
        }
        return allPos
    }

    override fun p2(input: List<String>): String {
        val clouds = allPos().filter { (x, y, z) ->
            cloudLevel(input, x, y, z) > 0
        }.toMutableSet()

        var cloudCount = 0
        while (clouds.isNotEmpty()) {
            val visited = mutableSetOf<Triple<Int, Int, Int>>()
            val toVisit = mutableListOf(clouds.first())
            while (toVisit.isNotEmpty()) {
                val next = toVisit.removeFirst()
                if (next in visited) {
                    continue
                }
                visited.add(next)
                clouds.remove(next)
                val neighbors = listOf(
                    Triple(next.first + 1, next.second, next.third),
                    Triple(next.first - 1, next.second, next.third),
                    Triple(next.first, next.second + 1, next.third),
                    Triple(next.first, next.second - 1, next.third),
                    Triple(next.first, next.second, next.third + 1),
                    Triple(next.first, next.second, next.third - 1)
                )
                toVisit.addAll(neighbors.filter { it in clouds })
            }
            cloudCount++
        }
        return cloudCount.toString()
    }
}

fun main() {
    val input = readInput("Infi")
    Infi.part1(input).println()
    Infi.part2(input).println()
}
