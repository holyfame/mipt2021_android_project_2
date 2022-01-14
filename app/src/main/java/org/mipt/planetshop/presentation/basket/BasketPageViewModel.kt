package org.mipt.planetshop.presentation.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.mipt.planetshop.domain.BasketRepository
import org.mipt.planetshop.domain.entity.Planet
import javax.inject.Inject

class BasketPageViewModel (
    private val basketRepository: BasketRepository
) : ViewModel() {

    private val _basketState = MutableLiveData<List<Planet>>()
    val basketState = _basketState

    init {
        viewModelScope.launch {
            _basketState.value = basketRepository.getPlanets()
        }
    }

    fun clearBasket() {
        viewModelScope.launch {
            basketRepository.clear()
            basketState.value = basketRepository.getPlanets()
        }
    }

    fun remBasketItem(pos : Int) {
        viewModelScope.launch {
            basketRepository.removePlanet(pos)
            basketState.value = basketRepository.getPlanets()
        }
    }


}