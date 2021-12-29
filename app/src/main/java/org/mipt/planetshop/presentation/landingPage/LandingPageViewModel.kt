package org.mipt.planetshop.presentation.landingPage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.mipt.planetshop.data.network.NasaApi
import org.mipt.planetshop.domain.PlanetRepository
import org.mipt.planetshop.domain.entity.Planet
import retrofit2.Retrofit
import retrofit2.create

class LandingPageViewModel(
    private val planetRepository: PlanetRepository
) : ViewModel() {

    private val _dateState = MutableLiveData(0)
    val dateState: LiveData<Int> = _dateState

    private val _planetsList = MutableLiveData<List<Planet>>()
    val planetList: LiveData<List<Planet>> = _planetsList

    init {
        viewModelScope.launch {
            val planets: List<Planet> = planetRepository.getPlanets()
            _planetsList.value = planets
        }
    }

    fun onAdd() {
        _dateState.value = _dateState.value?.plus(1)
    }
}