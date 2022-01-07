package org.mipt.planetshop.domain

import org.mipt.planetshop.domain.entity.MarsWeather

interface MarsWeatherRepository {
    suspend fun getMarsWeather(): MarsWeather
}