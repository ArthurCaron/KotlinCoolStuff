package functions

// Read more: https://kotlinlang.org/docs/operator-overloading.html

data class Point(val x: Int, val y: Int) {
    operator fun times(value: Int) = Point(x * value, y * value)
    operator fun invoke() {
        println("$x and $y")
    }
}

fun main() {
    (Point(1, 3) * 3)()
}