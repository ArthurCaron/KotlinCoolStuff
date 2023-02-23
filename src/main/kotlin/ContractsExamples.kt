import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

// Read more: https://www.baeldung.com/kotlin/contracts
// And: https://kotlinlang.org/docs/whatsnew13.html#contracts

// Note: this feature is still experimental (since Kotlin 1.3, they are being careful)

// The Kotlin compiler is smart and can figure out some things to make the code more readable
// A classic example is the following:
fun bazzz(nullableList: List<String>?) {
    if (nullableList != null) {
        println(nullableList.size) // compiles because the compiler will cast to List<String> automatically
    }
}

// But, as soon as that check is done elsewhere, it won't work anymore:
fun check(nullableList: List<String>?) = nullableList != null

fun bazou(nullableList: List<String>?) {
    if (check(nullableList)) {
//        println(nullableList.size) // does NOT compiles, no smart cast is possible here
    }
}

// The contracts come to the rescue!
// They give information to the compiler to help it make decisions
@ExperimentalContracts
fun improvedCheck(nullableList: List<String>?): Boolean {
    contract {
        returns(true) implies (nullableList != null)
    }
    return nullableList != null
}

@ExperimentalContracts
fun bazabou(nullableList: List<String>?) {
    if (improvedCheck(nullableList)) {
        println(nullableList.size) // Now it works!
    }
}

// Not going any further, RTFM :)
// And really, it's experimental, it shouldn't be used
