package org.mipt.planetshop.data

import org.mipt.planetshop.data.network.NasaApi
import org.mipt.planetshop.domain.PlanetRepository
import org.mipt.planetshop.domain.entity.Planet

class PlanetRepositoryImpl(
    private val nasaApi: NasaApi
) : PlanetRepository {
    override suspend fun getPlanets(): List<Planet> {
        return nasaApi.getPlanets(startDate = "2015-10-10", endDate = "2015-10-15").mapNotNull {
            Planet(
                url = it.url ?: return@mapNotNull null
            )
        }
    }
}