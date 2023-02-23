import java.util.function.BiFunction
import java.util.function.Consumer

// Read more: https://kotlinlang.org/docs/type-aliases.html

// Type aliases are syntactic sugar, they are replaced by the corresponding type when compiled

typealias AwesomeFunctionWithDomainSpecificName = BiFunction<Map<String, Any>, List<Array<Int>>, Consumer<Set<List<Double>>>>

fun myAwfulMethod(func: BiFunction<Map<String, Any>, List<Array<Int>>, Consumer<Set<List<Double>>>>): String {
    return "meh"
}

fun myAwfulButBetterMethod(func: AwesomeFunctionWithDomainSpecificName):  String {
    return "meh-okay"
}

// ---

class A {
    inner class Inner {
        inner class ThatIsALot {
            inner class PleaseStop {
                inner class Builder
            }
        }
    }
}

typealias ABuilder = A.Inner.ThatIsALot.PleaseStop.Builder
