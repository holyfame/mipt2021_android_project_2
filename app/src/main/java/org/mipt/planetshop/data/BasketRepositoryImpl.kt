package org.mipt.planetshop.data

import org.mipt.planetshop.domain.BasketRepository
import org.mipt.planetshop.domain.entity.Planet

class BasketRepositoryImpl : BasketRepository {
    private val planetList: ArrayList<Planet> = ArrayList<Planet>()

    override suspend fun removePlanet(pos: Int) {
        planetList.removeAt(pos)
    }

    override suspend fun getPlanets(): List<Planet> {
        return planetList
    }

    override suspend fun addPlanet(planet: Planet) {
        planetList.add(planet)
    }

    override suspend fun clear() {
        planetList.clear()
    }
}