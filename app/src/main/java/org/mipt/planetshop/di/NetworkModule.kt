package org.mipt.planetshop.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor.Chain
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.mipt.planetshop.data.MarsWeatherRepositoryImpl
import org.mipt.planetshop.data.PlanetRepositoryImpl
import org.mipt.planetshop.data.network.NasaApi
import org.mipt.planetshop.data.network.NasaApi.Companion.api_key
import org.mipt.planetshop.domain.MarsWeatherRepository
import org.mipt.planetshop.domain.PlanetRepository
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit.SECONDS

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideNasaApi(): NasaApi = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov")
        .client(
            OkHttpClient.Builder()
            .addInterceptor(
            HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .connectTimeout(30, SECONDS)
            .callTimeout(   30, SECONDS)
            .readTimeout(   30, SECONDS)
            .writeTimeout(  30, SECONDS)
            .build()
        )
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

    @Provides
    fun getMarsRepository(nasaApi: NasaApi): MarsWeatherRepository = MarsWeatherRepositoryImpl(nasaApi)


}