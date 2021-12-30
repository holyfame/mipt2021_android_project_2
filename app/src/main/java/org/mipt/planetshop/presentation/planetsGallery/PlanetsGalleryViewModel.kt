package org.mipt.planetshop.presentation.planetsGallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mipt.planetshop.domain.PlanetRepository
import org.mipt.planetshop.domain.entity.Planet
import org.mipt.planetshop.presentation.common.launchWithErrorHandler

class PlanetsGalleryViewModel(
    private val planetRepository: PlanetRepository
) : ViewModel() {

    private val _planetsList = MutableLiveData<List<Planet>>()
    val planetList: LiveData<List<Planet>> = _planetsList

    init {
//        viewModelScope.launchWithErrorHandler {
        viewModelScope.launch {
            val planets: List<Planet> = planetRepository.getPlanets("2020-10-01", "2020-10-10")
            _planetsList.value = planets
        }
    }

}