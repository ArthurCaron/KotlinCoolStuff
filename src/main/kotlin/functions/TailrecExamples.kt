package functions

import kotlin.math.max

// Read more: https://kotlinlang.org/docs/functions.html
// Read more: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-deep-recursive-function/

// Kotlin also as the tailrec modifier
// To be able to use it, a function must call itself as the last operation it does
// With tailrec, a recursive function will be optimized by the compiler to not be recursive anymore, thus eliminating the possibility of a stack overflow
tailrec fun fibonacci(n: Int, a: Int = 0, b: Int = 1): Int =
    when (n) {
        0 -> a
        1 -> b
        else -> fibonacci(n - 1, b, a + b)
    }

// There is another, simpler option, Deep Recursive Functions: (note: it doesn't do the same thing, but can solve the same problem)
class Tree(val left: Tree? = null, val right: Tree? = null)

val deepTree = generateSequence(Tree()) { Tree(it) }.take(100_000).last()

fun depth(t: Tree?): Int =
    if (t == null)
        0
    else max(
        depth(t.left),
        depth(t.right)
    ) + 1

fun bazoz() {
    println(depth(deepTree)) // StackOverflowError
}

// This will provoke a StackOverflowError because of the huge stack that will be generated with all of the recursive calls
// You can write the same method like so:
val deepDepth = DeepRecursiveFunction<Tree?, Int> { t ->
    if (t == null)
        0
    else max(
        callRecursive(t.left),
        callRecursive(t.right)
    ) + 1
}

fun bazozo() {
    println(deepDepth(deepTree)) // Okay!
}

// You can even do that:
val mutualRecursion = object {
    val even: DeepRecursiveFunction<Tree?, Int> = DeepRecursiveFunction { t ->
        if (t == null) 0 else odd.callRecursive(t.left) + odd.callRecursive(t.right) + 1
    }
    val odd: DeepRecursiveFunction<Tree?, Int> = DeepRecursiveFunction { t ->
        if (t == null) 0 else even.callRecursive(t.left) + even.callRecursive(t.right)
    }
}

// DeepRecursiveFunction keeps its stack on the heap, preventing stack overflow errors
