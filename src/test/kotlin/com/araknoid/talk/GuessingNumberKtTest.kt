package com.araknoid.talk

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.Charset

class GuessingNumberKtTest {
    private val charset = Charset.forName("utf-8")

    @Test
    fun `guess wrong and then guess right`() {

        val consoleOutput = ByteArrayOutputStream()
        val inputs = listOf("Carlo", "1", "y", "2", "n")

        System.setOut(PrintStream(consoleOutput))
        System.setIn(inputs.joinToString("\n").byteInputStream(charset))

        game { 2 }

        assertThat(consoleOutput.toString(charset))
            .isEqualTo(
                """What is your name?
                    |Hello, Carlo, welcome to the game!
                    |Dear Carlo, please guess a number from 1 to 5:
                    |You guessed wrong, Carlo! The number was: 2
                    |Do you want to continue, Carlo?
                    |Dear Carlo, please guess a number from 1 to 5:
                    |You guessed right, Carlo!
                    |Do you want to continue, Carlo?
                    |""".trimMargin()
            )
    }

    @Test
    fun `should continue if input is not an n`() {

        val consoleOutput = ByteArrayOutputStream()
        val inputs = listOf("Carlo", "1", "H", "2", "n")

        System.setOut(PrintStream(consoleOutput))
        System.setIn(inputs.joinToString("\n").byteInputStream(charset))

        game { 2 }

        assertThat(consoleOutput.toString(charset))
            .isEqualTo(
                """What is your name?
                    |Hello, Carlo, welcome to the game!
                    |Dear Carlo, please guess a number from 1 to 5:
                    |You guessed wrong, Carlo! The number was: 2
                    |Do you want to continue, Carlo?
                    |Dear Carlo, please guess a number from 1 to 5:
                    |You guessed right, Carlo!
                    |Do you want to continue, Carlo?
                    |""".trimMargin()
            )
    }

    @Test
    fun `guess wrong if input is not an number`() {

        val consoleOutput = ByteArrayOutputStream()
        val inputs = listOf("Carlo", "z", "y", "2", "n")

        System.setOut(PrintStream(consoleOutput))
        System.setIn(inputs.joinToString("\n").byteInputStream(charset))

        game { 2 }

        assertThat(consoleOutput.toString(charset))
            .isEqualTo(
                """What is your name?
                    |Hello, Carlo, welcome to the game!
                    |Dear Carlo, please guess a number from 1 to 5:
                    |You guessed wrong, Carlo! The number was: 2
                    |Do you want to continue, Carlo?
                    |Dear Carlo, please guess a number from 1 to 5:
                    |You guessed right, Carlo!
                    |Do you want to continue, Carlo?
                    |""".trimMargin()
            )
    }
}