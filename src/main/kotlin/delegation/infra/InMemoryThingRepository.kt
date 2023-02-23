package delegation.infra

import delegation.domain.ThingRepository

class InMemoryThingRepository : ThingRepository {
    private var stuff: String = ""

    override fun saveThat(stuff: String) {
        this.stuff = stuff
    }
}