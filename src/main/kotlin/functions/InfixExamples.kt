package functions// Read more: https://kotlinlang.org/docs/functions.html#infix-notation

// Let's say we want to be able to write:

var x = 0
fun doesntMatter(): String {
    if (x isEqualTo 0) {
        return "1"
    } else {
        return "0"
    }
}

// You can define it as:
infix fun Int.isEqualTo(other: Int) = this == other

// You need:
// to add "infix"
// the method to be a member method or an extension function
// the method to have only one parameter (with no default)

// An example from the common library:
var pair: Pair<String, Int> = "stuff" to 15

// It's actually:
var pere: Pair<String, Int> = "stuff".to(15)


// Other example:
val strings = listOf("a", "b", "c")
val result = strings.filter { it.length == 5 }.sortedBy { it }.map { it.uppercase() }
val result2 = strings infixFilter { it.length == 5 } infixSortedBy { it } infixMap { it.uppercase() }

infix fun List<String>.infixFilter(predicate: (String) -> Boolean) = filter(predicate)
infix fun List<String>.infixSortedBy(selector: (String) -> String) = sortedBy(selector)
infix fun List<String>.infixMap(transform: (String) -> String) = map(transform)
