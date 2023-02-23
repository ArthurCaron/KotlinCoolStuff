package delegation.domain

interface ThingRepository {
    fun saveThat(stuff: String)
}