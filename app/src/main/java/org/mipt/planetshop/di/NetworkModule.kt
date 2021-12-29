package org.mipt.planetshop.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.mipt.planetshop.data.PlanetRepositoryImpl
import org.mipt.planetshop.data.network.NasaApi
import org.mipt.planetshop.domain.PlanetRepository
import retrofit2.Retrofit
import retrofit2.create

object NetworkModule {

    private val nasaApi: NasaApi = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov")
        .addConverterFactory(
            Json(builderAction = {
                isLenient = true
                ignoreUnknownKeys = true
            }).asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create()

    private var repository: PlanetRepository? = null

    fun getRepository(): PlanetRepository =
        repository ?: PlanetRepositoryImpl(nasaApi).also { repository = it }
}