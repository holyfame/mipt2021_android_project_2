package org.mipt.planetshop.presentation.marsWeather

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.MarsWeatherBinding
import org.mipt.planetshop.domain.entity.MarsWeather
import org.mipt.planetshop.presentation.common.BaseFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MarsWeatherFragment:BaseFragment(R.layout.mars_weather) {

    private val viewBinding by viewBinding(MarsWeatherBinding::bind)
    private val viewModel by viewModels<MarsWeatherViewModel>()

    private val previousWeather = MarsWeather("SOL: 1098","Min Temperature = -12.146 F","Max Temperature = -93.348 F", "28 December 2021")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weatherState.observe(viewLifecycleOwner) { state: WeatherState ->
            when (state) {
                is WeatherState.Error -> {
                    val textError = getString(R.string.mars_errorAPI_text)
                    viewBinding.dateText.text =    previousWeather.date
                    viewBinding.solText.text =     previousWeather.sol
                    viewBinding.minTempText.text = previousWeather.mnAT
                    viewBinding.maxTempText.text = previousWeather.mxAT
                    viewBinding.marsHeader2.text = "Актуальные данные еще не получены Марсоходом"
                }
                is WeatherState.Loading -> {
                    val textLoading = getString(R.string.mars_loadingAPI)
                    viewBinding.dateText.text =    textLoading
                    viewBinding.solText.text =     textLoading
                    viewBinding.minTempText.text = textLoading
                    viewBinding.maxTempText.text = textLoading
                }
                is WeatherState.Success -> {

                    val dt = LocalDateTime.parse(state.weather.date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
                    viewBinding.dateText.text = dt.formatForHistory()
                    viewBinding.solText.text =     "Solar day: ${state.weather.sol}"
                    viewBinding.minTempText.text = "Min Temperature = ${state.weather.mnAT} F"
                    viewBinding.maxTempText.text = "Max Temperature = ${state.weather.mxAT} F"
                }
            }
        }
}}

fun LocalDateTime.formatForHistory(): String {
    return format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
}
