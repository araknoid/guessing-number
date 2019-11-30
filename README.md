# Guessing Number Kata - Refactoring

The main objective of this kata is to take a procedural program and incrementally refactor to a functional one.  
This is the Kotlin version of the [FP to the Max](https://www.youtube.com/watch?v=sxudIMiOo68) in Scala made by John De Goes.

#### Libraries
[Arrow](https://arrow-kt.io/)  
[JUnit](https://junit.org/junit4/)  
[AssertJ](https://joel-costigliola.github.io/assertj/)

#### Structure
It contains 3 branches:
* master - starting point of the Kata
* coverage - code covered by tests
* refactoring - functional refactoring with an incremental approach

#### Game
This is an example of the guessing number game:
```
What is your name?
Carlo
Hello, Carlo, welcome to the game!
Dear Carlo, please guess a number from 1 to 5:
1
You guessed right, Carlo!
Do you want to continue, Carlo?
y
Dear Carlo, please guess a number from 1 to 5:
2
You guessed wrong, Carlo! The number was: 4
Do you want to continue, Carlo?
n
```