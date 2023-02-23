package functions

// Read more: https://kotlinlang.org/docs/inline-functions.html

// Each function implies a cost at runtime
// inlining is the process of copying the code of the function where the call site is
// thus the function doesn't actually exist, and no costs are incurred (just like inline classes)

inline fun execute(action: () -> Unit) {
    action()
}

fun main() {
    execute {
        print("Hello ")
        print("World")
    }
}

// would be compiled to:
fun main2() {
    print("Hello ")
    print("World")
}


// Since inlining increases the size of the source code, it is discouraged if you don't have a function parameter:
inline fun add3(value: Int) = value + 3 // hence the warning


// Using inline functions, since their code is moved to the caller, you can return from the parent enclosing function
fun doThisNotInlined(func: (String) -> Int) {
    func("hello")
}

inline fun doThis(func: (String) -> Int) {
    func("hello")
}

fun test() {
    // Not allowed:
//    doThisNotInlined {
//        if (it == "hello") {
//            return
//        } else {
//            1
//        }
//    }
    doThis {
        if (it == "hello") {
            return
        } else {
            1
        }
    }
}

// An example you probably saw in action:
fun hasZeros(ints: List<Int>): Boolean {
    ints.forEach {
        if (it == 0) return true // returns from hasZeros, and it works because "forEach is inlined"
    }
    return false
}

// noinline and crossinline
// You might have seen those terms when looking at inlining, but what do they mean?
// when you inline a function, it also inlines all of the parameter functions, but that limits what you can do with them
// in the next example, if wouldn't be possible to pass "logger" to the function "flush" if "logger" is inlined.
// so we make it "noinlined" so it stays a lambda in the final code

fun isDebug() = true

inline fun doOnDebug(
    noinline logger: (String) -> Unit,
    block: () -> Unit
) {
    if (isDebug()) {
        logger("[LOG] Running doOnDebug...")
        block()
        logger("[LOG] Flushing the log...")
        val setInVar = logger // noinline is necessary here
        flush(logger) // noinline is necessary here
    }
}

fun flush(logger: (String) -> Unit) {
    // Flush the logger here
}

fun main3() {
    doOnDebug({ println(it) }, {
        println("I am a block")
    })
}

// will turn into:

fun main4() {
    if (isDebug()) {
        val logger: (String) -> Unit = { println(it) } // as you can see, the function is preserved (in all its glory)
        logger("[LOG] Running doOnDebug...")
        println("I am a block") // notice how this function was inlined
        logger("[LOG] Flushing the log...")
        val setInVar = logger
        flush(logger)
    }
}

// As for crossinline, remember this example?
//inline fun doThis(func: (String) -> Int) {
//    func("hello")
//}
//
//fun test() {
//    doThis {
//        if (it == "hello") {
//            return
//        } else {
//            1
//        }
//    }
//}

// Well, let's say we DON'T want to return to test, maybe we want to reset some data first
// then we would use crossinline to prevent that type of return (non local return)
inline fun doThat(crossinline func: (String) -> Int) {
    func("hello")
}

fun test2() {
    doThat {
        if (it == "hello") {
//             return // Doesn't compile
            return@doThat 2
        } else {
            1
        }
    }
    var doSomethingBlaBlaResetData = true
}

// If you want a full example (and better explained): https://ncorti.com/blog/noinline-and-crossline-once-for-all