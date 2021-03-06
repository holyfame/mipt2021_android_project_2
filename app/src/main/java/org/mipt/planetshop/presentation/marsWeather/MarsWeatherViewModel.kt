package org.mipt.planetshop.presentation.marsWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import org.mipt.planetshop.domain.MarsWeatherRepository
import org.mipt.planetshop.domain.entity.MarsWeather
import org.mipt.planetshop.presentation.common.launchWithErrorHandler
import javax.inject.Inject

@HiltViewModel
class MarsWeatherViewModel @Inject constructor(
    private val marsWeatherRepository: MarsWeatherRepository) : ViewModel() {

    private val _weatherState = MutableLiveData<WeatherState>(WeatherState.Loading())
    val weatherState: LiveData<WeatherState> = _weatherState

    init {
        viewModelScope.launchWithErrorHandler(block = {
            val weatherData: MarsWeather = marsWeatherRepository.getMarsWeather()
            _weatherState.value = WeatherState.Success(weatherData)
        }, onError = {
            _weatherState.value = WeatherState.Error(it)
        })
    }
}

sealed class WeatherState {
    class Loading() : WeatherState()
    class Success(val weather: MarsWeather) : WeatherState()
    class Error(val throwable: Throwable) : WeatherState()
}