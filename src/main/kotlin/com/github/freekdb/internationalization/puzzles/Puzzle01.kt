package com.github.freekdb.internationalization.puzzles


fun main() {
    println("Length limits on messaging platforms.")
    val description = "The total cost in cents of all your messages is"
//    Puzzle01(verbose = true).solvePuzzle("example 1", description)
    Puzzle01(verbose = true).solvePuzzle("puzzle input", description)
    // The total cost in cents of all your messages is: 107989.
}


class Puzzle01(verbose: Boolean) : BasePuzzleSolver(verbose) {
    override fun solvePuzzle(inputLines: List<String>): Long {
        if (verbose) {
            inputLines.forEach { line ->
                println("${countBytes(line)}; ${countCharacters(line)}; $line")
            }
        }

        return inputLines.map(::messagePrice).sum().toLong()
    }

    private fun messagePrice(line: String): Int {
        val smsPrice = if (countBytes(line) <= 160) 11 else 0
        val tweetPrice = if (countCharacters(line) <= 140) 7 else 0
        val combinedPrice = smsPrice + tweetPrice

        return if (combinedPrice == 18) 13 else combinedPrice
    }

    private fun countBytes(line: String): Int =
        line.toByteArray().size

    private fun countCharacters(line: String): Int =
        line.length

    @Suppress("SpellCheckingInspection")
    override val inputLinesExamples = listOf(
        "néztek bele az „ártatlan lapocskába“, mint ahogy belenézetlen mondták ki rá a halálos itéletet a sajtó csupa 20–30 éves birái s egyben hóhérai.\n" +
                "livres, et la Columbiad Rodman ne dépense que cent soixante livres de poudre pour envoyer à six milles son boulet d'une demi-tonne.  Ces\n" +
                "Люди должны были тамъ и сямъ жить въ палаткахъ, да и мы не были помѣщены въ посольскомъ дворѣ, который также сгорѣлъ, а въ двухъ деревянныхъ\n" +
                "Han hade icke träffat Märta sedan Arvidsons middag, och det hade gått nära en vecka sedan dess. Han hade dagligen promenerat på de gator, där"
    )
}
