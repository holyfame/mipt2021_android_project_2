package org.mipt.planetshop.presentation.marsWeather

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.MarsWeatherBinding
import org.mipt.planetshop.presentation.common.BaseFragment
import org.mipt.planetshop.presentation.landingPage.LandingPageViewModel
import org.mipt.planetshop.presentation.planetsGallery.PlanetsGalleryState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ISO_INSTANT

@AndroidEntryPoint
class MarsWeatherFragment:BaseFragment(R.layout.mars_weather) {

    private val viewBinding by viewBinding(MarsWeatherBinding::bind)
    private val viewModel by viewModels<MarsWeatherViewModel>()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weatherState.observe(viewLifecycleOwner) { state: WeatherState ->
            when (state) {
                is WeatherState.Error -> {
//                    viewBinding.planetsGalleryError.isVisible = true
//                    viewBinding.planetsGalleryProgress.isVisible = false
//                    viewBinding.planetsGalleryList.isVisible = false
                }
                is WeatherState.Loading -> {
//                    viewBinding.planetsGalleryError.isVisible = false
//                    viewBinding.planetsGalleryProgress.isVisible = true
//                    viewBinding.planetsGalleryList.isVisible = false
                }
                is WeatherState.Success -> {

                    val dt = LocalDateTime.parse(state.weather.date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
                    viewBinding.dateText.text = dt.formatForHistory()
                    viewBinding.solText.text =     "SOL: ${state.weather.sol}"
                    viewBinding.minTempText.text = "Min Temperature = ${state.weather.mnAT} F"
                    viewBinding.maxTempText.text = "Max Temperature = ${state.weather.mxAT} F"

//                    viewBinding.planetsGalleryProgress.isVisible = false
//                    viewBinding.planetsGalleryList.isVisible = true
//                    planetsGalleryAdapter.submitList(state.planets)
                }
            }

        }


}}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.formatForHistory(): String {
//    return format(DateTimeFormatter.ofLocalizedDateTime(dateStyle,timeStyle)
    return format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
}
