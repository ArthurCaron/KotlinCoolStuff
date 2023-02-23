package delegation.domain

open class DoStuffImpl(val thingRepository: ThingRepository) : DoStuff {
    override fun doThis() {
        thingRepository.saveThat("done that")
    }
}
