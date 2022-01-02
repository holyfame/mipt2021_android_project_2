package org.mipt.planetshop.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.mipt.planetshop.data.PlanetRepositoryImpl
import org.mipt.planetshop.data.network.NasaApi
import org.mipt.planetshop.domain.PlanetRepository
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideNasaApi(): NasaApi = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov")
        .addConverterFactory(
            Json(builderAction = {
                isLenient = true
                ignoreUnknownKeys = true
            }).asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create()

    @Provides
    fun getRepository(nasaApi: NasaApi): PlanetRepository = PlanetRepositoryImpl(nasaApi)

}