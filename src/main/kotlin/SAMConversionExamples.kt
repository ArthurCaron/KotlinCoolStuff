// Read more: https://kotlinlang.org/docs/fun-interfaces.html#sam-conversions

// functional interface
fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

// Normally, you would have to do:
val isEven = object : IntPredicate {
    override fun accept(i: Int): Boolean {
        return i % 2 == 0
    }
}

// But you can do:
val isItEven = IntPredicate { i -> i % 2 == 0 }

// But only for functional interfaces
