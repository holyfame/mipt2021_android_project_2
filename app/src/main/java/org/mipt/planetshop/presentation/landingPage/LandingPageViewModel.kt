package org.mipt.planetshop.presentation.landingPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.mipt.planetshop.domain.PlanetRepository
import org.mipt.planetshop.domain.entity.Planet
import org.mipt.planetshop.presentation.common.launchWithErrorHandler


class LandingPageViewModel() : ViewModel() {

    private val _dateState = MutableLiveData(0)
    val dateState: LiveData<Int> = _dateState

    fun onAdd() {
        _dateState.value = _dateState.value?.plus(1)
    }
}