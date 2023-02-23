package delegation

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// Read more: https://kotlinlang.org/docs/delegated-properties.html
// Other example: https://play.kotlinlang.org/byExample/07_Delegation/01_delegationPattern

class DelegationExamples {
    private val apiCallService = "whatever"

    private val expensiveDataToFetch: String by lazy {
        // only called if needed, when needed
        // called once, the functions.getResult is saved
        // synchronized by default (no risks with multithreading), can be changed if needed
        apiCallService
    }

    fun example(computeFoo: () -> String) {
        val memoizedFoo by lazy(computeFoo) // will only be computed if "someCondition" is true

        val someCondition = 42 == 84 / 2
        if (someCondition && memoizedFoo.isBlank()) {
            println(memoizedFoo)
        }
    }

    private var name: String by Delegates.observable("<no name>") { prop, old, new ->
        println("Send event, publish change to observers, store change, etc --- $old -> $new for $prop")
    }

    private var vetoableName: String by Delegates.vetoable("initial value") { prop, old, new ->
        if (prop.name == "var name") {
            true // variable value WILL be changed
        } else if (old == "oldValue") {
            true // variable value WILL be changed
        } else if (new == "YES") {
            false // variable value will not be changed
        } else {
            false // variable value will not be changed
        }
    }

    // We can delegate to another variable
    // In this example, we can keep the deprecated var but explicitly link it to the one that should be used
    var newName: Int = 0
    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
    var oldName: Int by this::newName



    // You can also create your own delegates:
    var varResource: String by ConsolePrintedStringDelegate("initial value")

    class ConsolePrintedStringDelegate(private var resource: String = "") {
        operator fun getValue(thisRef: Any, property: KProperty<*>): String {
            println("current value: $resource")
            return resource
        }
        operator fun setValue(thisRef: Any, property: KProperty<*>, value: Any?) {
            println("old value: $resource, new value: $value")
            if (value is String) {
                resource = value
            }
        }
    }
}
