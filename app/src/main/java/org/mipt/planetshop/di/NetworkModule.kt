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
import org.mipt.planetshop.data.PlanetRepositoryImpl
import org.mipt.planetshop.data.network.NasaApi
import org.mipt.planetshop.data.network.NasaApi.Companion.api_key
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
                // api-key у нас передается как часть запроса, а не в хедере.
                // Этот кусок тут для примера.
//            .addInterceptor { chain: Chain ->
//                val request = chain.request()
//                    .newBuilder()
//                    .header("api_key", api_key)
//                    .build()
//
//                chain.proceed(request)
//            }
            .addInterceptor(
            HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .connectTimeout(10, SECONDS)
            .callTimeout(10, SECONDS)
            .readTimeout(10, SECONDS)
            .writeTimeout(10, SECONDS)
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

}