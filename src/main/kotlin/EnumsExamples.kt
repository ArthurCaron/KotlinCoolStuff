// Read more: https://kotlinlang.org/docs/enum-classes.html#anonymous-classes


// Hey, enum values can implement methods!
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

// Instead of:
enum class Direction {
    NORTH, SOUTH, WEST, EAST;

    fun move(x: Int, y: Int): Pair<Int, Int> {
        return when (this) {
            NORTH -> x + 1 to y
            SOUTH -> x - 1 to y
            WEST -> x to y + 1
            EAST -> x to y - 1
        }
    }
}

// You can do:
enum class OneDirection {
    NORTH { override fun move(x: Int, y: Int) = x + 1 to y },
    SOUTH { override fun move(x: Int, y: Int) = x - 1 to y },
    WEST { override fun move(x: Int, y: Int) = x to y + 1 },
    EAST { override fun move(x: Int, y: Int) = x to y - 1 };

    abstract fun move(x: Int, y: Int): Pair<Int, Int>
}

// Not saying it's better, but I'm sure it can be useful

// You can also implement interfaces:
enum class ComparableStuff : IntPredicate {
    THING { override fun accept(i: Int) = i == 0 },
    STUFF { override fun accept(i: Int) = i > 0 },
    MISC { override fun accept(i: Int) = i < 0 };
}

