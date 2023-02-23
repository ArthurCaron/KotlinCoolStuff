import kotlin.random.Random

// Read more: https://kotlinlang.org/docs/scope-functions.html

// Scope functions are REALLY useful
// They basically let you execute a block of code on existing code in a "stream-like" style
// There are 5, 4 that are similar and a slightly different one:
// let, run, apply, also --- and with

// with: "enters" inside a variable, and then you can use "this" as if you were inside the object
fun fooz() {
    val list = mutableListOf("a", "b", "c")

    list.add("d")

    with(list) {
        add("e") // this == list, here
    }
}

// For the other 4, they are basically two axis they can differ on, and there are 4 to cover all possibilities
// They differ on the Object Reference, whether the object the scope function is called on will be referred as "this" or "it" (whether we are "inside" the object or not)
// And they differ on the kind of result they return, either the object they were called on, or the result of the lambda:

// Scope Function | Object Reference | Return Value
// let            | it               | Lambda result
// run            | this             | Lambda result
// apply          | this             | Context object
// also           | it               | Context object

// Here's a handy link with this table: https://kotlinlang.org/docs/scope-functions.html#function-selection

fun barz() {
    var sizeOfList = mutableListOf("a", "b", "c")
        .let {
            it.add("d")
            it.size
        }

    var sizeOfList2 = mutableListOf("a", "b", "c")
        .run {
            add("d")
            size
        }

    var list = mutableListOf("a", "b", "c")
        .apply {
            add("d")
        }

    var list2 = mutableListOf("a", "b", "c")
        .also {
            it.add("d")
            println(it)
        }
}

// First figure out if you want the result of the lambda or the original object, then pick between "this" or "it" depending on what's more convenient

// Additionally, there are two methods to filter on a single object, they can remove the need to add "ifs":
fun bazar() {
    var maybeEmpty = mutableListOf("a", "b", "c").takeIf { Random.nextBoolean() } ?: emptyList()

    maybeEmpty = mutableListOf("a", "b", "c").takeUnless { Random.nextBoolean() } ?: emptyList()

    mutableListOf("a", "b", "c")
        .takeIf { Random.nextBoolean() }
        ?.apply { add("d") }
        ?.let { println(it) }
}

// You can thus turn:
fun displaySubstringPosition(input: String, sub: String) {
    val index = input.indexOf(sub)
    if (index >= 0) {
        println("The substring $sub is found in $input.")
        println("Its start position is $index.")
    }
}

// into:
fun displaySubstringPositionButDifferent(input: String, sub: String) {
    input.indexOf(sub).takeIf { it >= 0 }?.let {
        println("The substring $sub is found in $input.")
        println("Its start position is $it.")
    }
}