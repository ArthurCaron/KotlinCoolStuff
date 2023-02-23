package functions

// Read more: https://kotlinlang.org/docs/functions.html

// You can define functions inside functions:
class Graph

fun search(graph: Graph): String {
    fun searchInternal(graph: Graph, depth: Int): String {
        return if (depth == 10) {
            "done"
        } else {
            searchInternal(graph, depth + 1)
        }
    }

    return searchInternal(graph, 0)
}

// You know extension functions:
fun String.print() {
    println(this)
}

// But you can use something similar called function literals with receiver:

fun doSomething(func: String.() -> Unit) {
    var myString = "hello"
    myString.func()
}

fun tmp() {
    doSomething {
        println(this) // here "this" is the String we call the function on
    }
}

// This is important for type safe builders:
class HTML {
    fun body() {}
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()  // create the receiver object
    html.init()        // pass the receiver object to the lambda
    return html
}

fun tmp2() {
    html {// lambda with receiver begins here
        body()   // calling a method on the receiver object
    }
}