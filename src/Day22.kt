object Day22 : Day() {
    override fun p1(input: List<String>): String {
        return input.map { generateSecretNumbers(it) }.sumOf { it.last().toLong() }.toString()
    }

    override fun p2(input: List<String>): String {
        val prices = input.map { secret -> generateSecretNumbers(secret).map { (it % 10).toInt() } }
        val deltas = prices.map { it.windowed(2).map { (first, second) -> second - first } }
        val scores: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
        deltas.forEachIndexed { index, delta ->
            for (windowIndex in 0..delta.size - 4) {
                val window = delta[windowIndex] + 9 * 1000000 + delta[windowIndex + 1] + 9 * 10000 + delta[windowIndex + 2] + 9 * 100 + delta[windowIndex + 3] + 9
                if (!scores.contains(index to window)) {
                    scores[index to window] = prices[index][windowIndex + 4]
                }
            }
        }

        val scoresByWindow: MutableMap<Int, Int> = mutableMapOf<Int, Int>().withDefault { 0 }
        scores.forEach { (_, list), v ->
            scoresByWindow[list] = scoresByWindow.getValue(list) + v
        }
        return scoresByWindow.values.max().toString()
    }

    private fun generateSecretNumbers(secret: String): List<Long> {
        val successiveSecrets = mutableListOf(secret.toLong())
        repeat(2000) {
            successiveSecrets += successiveSecrets.last().next()
        }
        return successiveSecrets
    }
}

private fun Long.next(): Long {
    var result = this * 64
    var secret = result.mix(this).prune()
    result = secret / 32
    secret = result.mix(secret).prune()
    result = secret * 2048
    return result.mix(secret).prune()
}

private fun Long.mix(secret: Long): Long = this.xor(secret)

private fun Long.prune(): Long = this % 16777216

fun main() {
    val input = readInput("Day22")
    Day22.part1(input).println()
    Day22.part2(input).println()
}
