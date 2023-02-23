// Read more: https://kotlinlang.org/docs/object-declarations.html#object-expressions

// Objects are instantiation of anonymous classes
// You can create anything:
val helloWorld = object {
    val hello = "Hello"
    val world = "World"
    override fun toString() = "$hello $world"
}

// When an anonymous object is used as a type of a local or private declaration (function or property), all its members are accessible via this function or property:
class C {
    private fun getObject() = object {
        val x: String = "functions.getX"
    }

    fun printX() {
        println(getObject().x)
    }
}

// Using "object" instead of "class" makes it a singleton:
object Repository {
    val myData: String = ""
}

var getMyData = Repository.myData
// Note: this is lazily initialized


// Since Kotlin 1.9, you can make data objects, that are singletons that benefits from the advantages of data classes (notably: a nicely formatted string representation)
sealed class ReadResult {
    data class Number(val value: Int): ReadResult()
    data class Text(val value: String): ReadResult()
    /*data*/ object EndOfFile: ReadResult() // only in Kotlin 1.9
}

fun main() {
    println(ReadResult.Number(1)) // Number(value=1)
    println(ReadResult.Text("Foo")) // Text(value=Foo)
    println(ReadResult.EndOfFile) // EndOfFile
}

// Companion objects are objects as well, and even though they look like static members, they are an actual singleton object, therefore they can implement interfaces:
class MyClass {
    companion object : Comparable<Int> {
        override fun compareTo(other: Int): Int {
            TODO("Not yet implemented")
        }
    }
}
