class InitOrderDemo(private var name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

// Will functions.print:

/*
First property: hello
First initializer block that prints hello
Second property: 5
Second initializer block that prints 5
 */

// Note: the init blocks will always be executed after the primary constructor (it becomes part of it)
// If you have a secondary constructor, since it will have to call the primary constructor first,
// the init blocks will be executed before the code inside the secondary constructor

class InitOrderDemo2(private var name: String) {
    constructor(name: String, company: String) : this(name) {
        this.company = company
    }

    private lateinit var company: String

    init {
        println("$company isn't initialized here, so it will throw an exception")
    }
}

fun main() {
    InitOrderDemo2("name", "company")
}
