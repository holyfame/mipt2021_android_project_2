package org.mipt.planetshop.domain

import org.mipt.planetshop.domain.entity.Planet

interface BasketRepository {
    suspend fun getPlanets(): List<Planet>
    suspend fun addPlanet(planet: Planet)
    suspend fun removePlanet(pos: Int)
    suspend fun clear()
}