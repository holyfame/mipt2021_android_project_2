package org.mipt.planetshop.presentation.planetsGallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import org.mipt.planetshop.domain.PlanetRepository
import org.mipt.planetshop.domain.entity.Planet
import org.mipt.planetshop.presentation.common.SingleLiveEvent
import org.mipt.planetshop.presentation.common.launchWithErrorHandler
import java.lang.Error
import javax.inject.Inject

@HiltViewModel
class PlanetsGalleryViewModel @Inject constructor(
    private val planetRepository: PlanetRepository
) : ViewModel() {

    private val _planetsGalleryState = MutableLiveData<PlanetsGalleryState>(PlanetsGalleryState.Loading())
    val planetsGalleryState: LiveData<PlanetsGalleryState> = _planetsGalleryState

    private val _openDetailAction = SingleLiveEvent<Planet>()
    val openDetailAction: LiveData<Planet> = _openDetailAction

    init {
        viewModelScope.launchWithErrorHandler(block = {
            val planets: List<Planet> = planetRepository.getPlanets("2020-10-01", "2020-10-10")
            _planetsGalleryState.value = PlanetsGalleryState.Success(planets)
        }, onError = {
            _planetsGalleryState.value = PlanetsGalleryState.Error(it)
        })
    }

    fun onPlanetClicked(planet: Planet) {
        _openDetailAction.value = planet
    }

}

sealed class PlanetsGalleryState {
    class Loading() : PlanetsGalleryState()
    class Success(val planets: List<Planet>) : PlanetsGalleryState()
    class Error(val throwable: Throwable) : PlanetsGalleryState()
}