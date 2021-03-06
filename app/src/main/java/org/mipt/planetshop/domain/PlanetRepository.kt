package org.mipt.planetshop.domain

import org.mipt.planetshop.domain.entity.Planet

interface PlanetRepository {
    suspend fun getPlanets(startDate: String, endDate: String): List<Planet>
}