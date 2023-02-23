// Read more: https://github.com/Kotlin/KEEP/blob/master/proposals/context-receivers.md#detailed-design

// This is also VERY experimental
// Context receivers lets you say "okay, when this code is called, it will have access to the context of an object of type X, Y and Z
// And thus we can call methods from inside those contexts
// It limits the usage of certain methods depending on the context where they are called:

context(Fruit)
fun isDisgusting() = !isGood()

interface Fruit {
    fun isGood(): Boolean
}

class Pear : Fruit {
    override fun isGood() = false
}

class Lychee : Fruit {
    override fun isGood() = true
}

class Car

fun main() {
    Pear().apply {
        isDisgusting()
    }

//     isDisgusting() // Does not compile

    Car().apply {
//         isDisgusting() // Does not compile
    }

    Lychee().isGood() // Just sayin'
}

