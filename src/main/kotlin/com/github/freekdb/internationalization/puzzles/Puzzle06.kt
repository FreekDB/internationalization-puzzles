package com.github.freekdb.internationalization.puzzles


fun main() {
    println("Mojibake puzzle dictionary.")
//    Puzzle06(verbose = true).solvePuzzle(inputId = "example 1", description = "Solution")
    Puzzle06(verbose = false).solvePuzzle(inputId = "puzzle input", description = "Solution")
    // Solution: 11252.
}


class Puzzle06(verbose: Boolean) : BasePuzzleSolver(verbose) {
    override fun solvePuzzle(inputLines: List<String>): Long {
        val words = getWords(inputLines)
        val crosswordLines = getCrosswordLines(inputLines)

        return calculateSolution(words, crosswordLines)
    }

    private fun getCrosswordLines(inputLines: List<String>) =
        inputLines
            .dropWhile { line -> line.isNotBlank() }
            .drop(1)
            .map { it.trim().lowercase() }

    private fun getWords(inputLines: List<String>) =
        inputLines
            .takeWhile { line -> line.isNotBlank() }
            .mapIndexed { lineIndex, word ->
                val lineNumber = lineIndex + 1

                when {
                    (lineNumber % 15 == 0) -> ungarbleText(ungarbleText(word))
                    (lineNumber % 3 == 0) -> ungarbleText(word)
                    (lineNumber % 5 == 0) -> ungarbleText(word)
                    else -> word
                }.lowercase()
            }

    private fun ungarbleText(text: String) =
        String(text.toByteArray(Charsets.ISO_8859_1), Charsets.UTF_8)

    private fun calculateSolution(words: List<String>, crosswordLines: List<String>): Long {
        if (verbose) {
            println("Matching crossword lines to words:")
        }

        return crosswordLines.sumOf { crosswordLine ->
            val letterIndex = crosswordLine.indexOfFirst { it != '.' }

            val matchingWord = words
                .filter { word -> word.length == crosswordLine.length }
                .find { word -> word[letterIndex] == crosswordLine[letterIndex] }

            val wordNumber = words.indexOf(matchingWord) + 1

            if (verbose) {
                println("Match: $crosswordLine -> $matchingWord ($wordNumber).")
            }

            wordNumber
        }.toLong()
    }

    @Suppress("SpellCheckingInspection")
    override val inputLinesExamples = listOf(
        "\n" +
                "geléet\n" +
                "träffs\n" +
                "religiÃ«n\n" +
                "tancées\n" +
                "kÃ¼rst\n" +
                "roekoeÃ«n\n" +
                "skälen\n" +
                "böige\n" +
                "fÃ¤gnar\n" +
                "dardÃ©es\n" +
                "amènent\n" +
                "orquestrÃ¡\n" +
                "imputarão\n" +
                "molières\n" +
                "pugilarÃ\u0083Â£o\n" +
                "azeitámos\n" +
                "dagcrème\n" +
                "zÃ¶ger\n" +
                "ondulât\n" +
                "blÃ¶kt\n" +
                "\n" +
                "   ...d...\n" +
                "    ..e.....\n" +
                "     .l...\n" +
                "  ....f.\n" +
                "......t.."
    )
}
