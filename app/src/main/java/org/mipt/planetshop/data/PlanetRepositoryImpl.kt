package org.mipt.planetshop.data

import org.mipt.planetshop.data.network.NasaApi
import org.mipt.planetshop.domain.PlanetRepository
import org.mipt.planetshop.domain.entity.Planet

class PlanetRepositoryImpl(
    private val nasaApi: NasaApi
) : PlanetRepository {
    override suspend fun getPlanets(startDate: String, endDate: String): List<Planet> {
        return nasaApi.getPlanets(startDate = startDate, endDate = endDate).mapNotNull { response ->
            Planet(
                url = response.url ?: return@mapNotNull null,
                title = response.title ?: "",
                explanation = response.explanation ?: ""
            )
        }
    }
}