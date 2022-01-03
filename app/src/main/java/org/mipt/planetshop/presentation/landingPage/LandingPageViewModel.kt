package org.mipt.planetshop.presentation.landingPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import org.mipt.planetshop.domain.PlanetRepository
import org.mipt.planetshop.presentation.common.launchWithErrorHandler
import javax.inject.Inject

@HiltViewModel
class LandingPageViewModel @Inject constructor() : ViewModel() {

    private val _counterState = MutableLiveData(0)
    val counterState: LiveData<Int> = _counterState

    private val _startDateState = MutableLiveData(DateState.VALID)
    val startDateState: LiveData<DateState> = _startDateState

    private val _endDateState = MutableLiveData(DateState.VALID)
    val endDateState: LiveData<DateState> = _endDateState

    fun onAdd() {
        _counterState.value = _counterState.value?.plus(1)
    }

    fun search(startDate: String, endDate: String) {
        var hasError = false
        if (startDate.isBlank()) {
            _startDateState.value = DateState.EMPTY
            hasError = true
        }
        if (endDate.isBlank()) {
            _endDateState.value = DateState.EMPTY
            hasError = true
        }

        if (hasError) return

        _startDateState.value = DateState.VALID
        _endDateState.value = DateState.VALID
    }
}

enum class DateState {
    EMPTY, VALID
}