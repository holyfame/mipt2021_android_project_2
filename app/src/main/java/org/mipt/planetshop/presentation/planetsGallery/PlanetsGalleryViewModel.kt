package org.mipt.planetshop.presentation.planetsGallery

import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import org.mipt.planetshop.domain.PlanetRepository
import org.mipt.planetshop.domain.entity.Planet
import org.mipt.planetshop.presentation.common.SingleLiveEvent
import org.mipt.planetshop.presentation.common.launchWithErrorHandler

class PlanetsGalleryViewModel
@AssistedInject
constructor(
    private val planetRepository: PlanetRepository,
    @Assisted("startDate") private val startDate: String,
    @Assisted("endDate") private val endDate: String
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("startDate") startDate: String,
            @Assisted("endDate") endDate: String
        ): PlanetsGalleryViewModel
    }

    private val _planetsGalleryState = MutableLiveData<PlanetsGalleryState>(PlanetsGalleryState.Loading())
    val planetsGalleryState: LiveData<PlanetsGalleryState> = _planetsGalleryState

    private val _openDetailAction = SingleLiveEvent<Planet>()
    val openDetailAction: LiveData<Planet> = _openDetailAction

    init {
        viewModelScope.launchWithErrorHandler(block = {
            val planets: List<Planet> = planetRepository.getPlanets(startDate, endDate)
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