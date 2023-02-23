// Read more: https://kotlinlang.org/docs/destructuring-declarations.html

// Destructuring is the process of creating multiple variables from one variable
// There are a lot of things you can destructure
// Basically, any type that implements "componentsN" functions can be destructured

fun any() {
    val pear = "fruit" to "disgusting"

    val (type, isGood) = pear

    // which will be compiled to:
    val type1 = pear.component1()
    val isGood1 = pear.component2()
}

// componentN functions are operator functions

// This doesn't compile, but it would if ThisIsADestructurableClass was a data class, because data classes implement the componentN functions automatically

//class ThisIsADestructurableClass(val first: String, val second: Int)
//fun foo() {
//    val (first, second) = ThisIsADestructurableClass("first", 2)
//}

class ThisIsADestructurableClass(val first: String, val second: Int) {
    operator fun component1() = first
    operator fun component2() = second
}
fun foo() {
    val (first, second) = ThisIsADestructurableClass("first", 2)
}

// maps return Pairs so they can be destructured:
fun bar() {
    val map = mapOf("first" to 1, "second" to 2)
    for ((key, value) in map) {
        println("$key: $value")
    }
}

// You can obviously use it in lambdas as well:
fun boo() {
    mapOf("first" to 1, "second" to 2)
        .mapValues { (_, value) -> "$value" } // And you can use "_" when you don't need one of the components
}



