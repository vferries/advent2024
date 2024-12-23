object Day23 : Day() {
    private val memo = mutableMapOf<Set<String>, Set<String>>()

    override fun p1(input: List<String>): String {
        val connections = connectionsMap(input)

        val setsOfThreeComputers = mutableSetOf<List<String>>()
        connections.forEach { (node, neighbors) ->
            for (n1 in neighbors) {
                for (n2 in connections.getValue(n1)) {
                    if (connections.getValue(n2).contains(node)) {
                        setsOfThreeComputers += listOf(node, n1, n2).sorted()
                    }
                }
            }
        }
        return setsOfThreeComputers.filter { list -> list.any { it.startsWith("t") } }.size.toString()
    }

    override fun p2(input: List<String>): String {
        val connections = connectionsMap(input)
        val bestCliques = connections.keys.map { findBestClique(setOf(it), connections) }
        return bestCliques.maxBy { it.size }.toList().sorted().joinToString(",")
    }

    private fun connectionsMap(input: List<String>): MutableMap<String, Set<String>> {
        val connections = mutableMapOf<String, Set<String>>().withDefault { setOf() }
        input.forEach { line ->
            val (c1, c2) = line.split("-")
            connections[c1] = connections.getValue(c1) + c2
            connections[c2] = connections.getValue(c2) + c1
        }
        return connections
    }

    private fun findBestClique(currentClique: Set<String>, connections: MutableMap<String, Set<String>>): Set<String> {
        if (!memo.contains(currentClique)) {
            val potentialNodes =
                connections.filterValues { neighbors -> neighbors.containsAll(currentClique) }.map { it.key }
            memo[currentClique] = if (potentialNodes.isEmpty()) {
                currentClique
            } else {
                potentialNodes.map { n -> findBestClique(currentClique + n, connections) }
                    .maxBy { it.size }
            }
        }
        return memo.getValue(currentClique)
    }
}

fun main() {
    val input = readInput("Day23")
    Day23.part1(input).println()
    Day23.part2(input).println()
}
