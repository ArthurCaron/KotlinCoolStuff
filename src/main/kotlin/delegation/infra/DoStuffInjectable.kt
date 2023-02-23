package delegation.infra

import delegation.domain.DoStuff
import delegation.domain.DoStuffImpl

/*@Service*/ // let's pretend I have the Spring dependency
class DoStuffInjectable(inMemoryThingRepository: InMemoryThingRepository) : DoStuff by DoStuffImpl(inMemoryThingRepository)

// Without delegation:

class DoStuffInjectableNoDel(inMemoryThingRepository: InMemoryThingRepository) : DoStuff {
    private val doStuffImpl = DoStuffImpl(inMemoryThingRepository)

    override fun doThis() {
        doStuffImpl.doThis()
    }
}

// Or we could make DoStuffImpl "open" and:

class DoStuffInjectableWithInheritance(inMemoryThingRepository: InMemoryThingRepository) : DoStuffImpl(inMemoryThingRepository)