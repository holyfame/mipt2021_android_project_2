package org.mipt.planetshop.presentation.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mipt.planetshop.domain.BasketRepository
import org.mipt.planetshop.domain.entity.Planet

class BasketPageViewModel(
    private val basketRepository: BasketRepository
) : ViewModel() {

    private val _basketState = MutableLiveData<List<Planet>>()
    val basketState = _basketState

    init {
        viewModelScope.launch {
            basketRepository.addPlanet(samplePlanet())
            basketRepository.addPlanet(samplePlanet())
            basketRepository.addPlanet(samplePlanet())
            _basketState.value = basketRepository.getPlanets()
        }
    }

    fun clearBasket() {
        viewModelScope.launch {
            basketRepository.clear()
            _basketState.value = basketRepository.getPlanets()
        }
    }

    private fun samplePlanet(): Planet {
        return Planet("https://apod.nasa.gov/apod/image/2001/BetelgeuseImagined_EsoCalcada_960.jpg","Betelgeuse Imagined", "Why is Betelgeuse fading?  No one knows.  Betelgeuse, one of the brightest and most recognized stars in the night sky, is only half as bright as it used to be only five months ago.  Such variability is likely just  normal behavior for this famously variable supergiant, but the recent dimming has rekindled discussion on how long it may be before Betelgeuse does go supernova.  Known for its red color, Betelgeuse is one of the few stars to be resolved by modern telescopes, although only barely.  The featured artist's illustration imagines how Betelgeuse might look up close. Betelgeuse is thought to have a complex and tumultuous surface that frequently throws impressive flares.  Were it to replace the Sun (not recommended), its surface would extend out near the orbit of Jupiter, while gas plumes would bubble out past Neptune.  Since Betelgeuse is about 700 light years away, its eventual supernova will not endanger life on Earth even though its brightness may rival that of a full Moon.  Astronomers -- both amateur and professional -- will surely continue to monitor Betelgeuse as this new decade unfolds.    Free Presentation: APOD Editor to show best astronomy images of 2019 -- and the decade -- in NYC on January 3")
    }
}