package com.example.androiddev.domain

object VectorScalar {
    fun generateVector(size: Int): List<Int> {
        return List(size) {
            (-10..10).random()
        }
    }

    fun scalarProduct(
        firstVector: List<Int>,
        secondVector: List<Int>
    ): Int {
        return firstVector
            .zip(secondVector)
            .sumOf { (first, second) -> first * second }
    }
}
