package com.example.calculadora.model

class Calculadora {
    companion object {
        fun calcular(num1: Int, num2: Int, op: Char): Int {
            return when (op) {
                '+' -> num1 + num2
                '-' -> num1 - num2
                '*' -> num1 * num2
                '/' -> if (num2 != 0) num1 / num2 else 0
                '%' -> num1 % num2
                else -> 0
            }
        }
    }
}
