package functions

// Read more: https://kotlinlang.org/docs/inline-functions.html#reified-type-parameters

// From a dictionary:
// Reify comes from the Latin word res, which means "thing," with the suffix -fy, meaning "make into" or "produce," which you know from verbs like "horrify" and "falsify."
// When you reify something abstract, you make it real.
// Basically, a reified type is a type you can access at runtime (a type you "make real")

fun <T> myGenericFun(c: Class<T>) {
    println(c.name)
    println(c)
}

inline fun <reified T> myGenericFun() {
    println(T::class.simpleName)
    println(T::class)
}

// for it to work, we NEED the function to be inlined.
// It's basically syntactic sugar: the function doesn't actually exists (after the compilation)
// And since the content of the function will be moved to the caller, T is not abstract anymore,
// the compiler knows the actual type and is thus not impacted by type erasure

// Another example:

open class SmartPhone

class IPhone : SmartPhone()

class Android : SmartPhone()

inline fun <reified T> List<SmartPhone>.findByType(): T? = find { it is T } as T

fun m() {
    val smartPhones = listOf(Android(), IPhone())
    smartPhones.findByType<Android>()
}

// instead of:

fun <T : Any> List<SmartPhone>.findByType(type: Class<T>): T = TODO("Couldn't be bothered to write that")

fun n() {
    val smartPhones = listOf(Android(), IPhone())
    smartPhones.findByType(Android::class.java)
}