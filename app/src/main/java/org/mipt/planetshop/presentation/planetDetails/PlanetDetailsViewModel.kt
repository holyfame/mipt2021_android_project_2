package org.mipt.planetshop.presentation.planetDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mipt.planetshop.domain.BasketRepository
import org.mipt.planetshop.domain.entity.Planet

class PlanetDetailsViewModel(
    private val basketRepository: BasketRepository
) : ViewModel() {

    fun addPlanetToBasket(planet: Planet) {
        viewModelScope.launch {
            basketRepository.addPlanet(planet)
        }
    }
}