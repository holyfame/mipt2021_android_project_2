package org.mipt.planetshop.presentation.planetsGallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.mipt.planetshop.domain.PlanetRepository
import org.mipt.planetshop.domain.entity.Planet
import org.mipt.planetshop.presentation.common.SingleLiveEvent
import org.mipt.planetshop.presentation.common.launchWithErrorHandler
import javax.inject.Inject

@HiltViewModel
class PlanetsGalleryViewModel @Inject constructor(
    private val planetRepository: PlanetRepository
) : ViewModel() {

    private val _planetsList = MutableLiveData<List<Planet>>()
    val planetList: LiveData<List<Planet>> = _planetsList

    private val _openDetailAction = SingleLiveEvent<Planet>()
    val openDetailAction: LiveData<Planet> = _openDetailAction

    init {
        viewModelScope.launchWithErrorHandler {
            val planets: List<Planet> = planetRepository.getPlanets("2020-10-01", "2020-10-10")
            _planetsList.value = planets
        }
    }

    fun onPlanetClicked(planet: Planet) {
        _openDetailAction.value = planet
    }

}