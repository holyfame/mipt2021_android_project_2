package org.mipt.planetshop.presentation.planetDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.PlanetDetailsBinding
import org.mipt.planetshop.di.BasketRepositoryProvider
import org.mipt.planetshop.domain.entity.Planet
import org.mipt.planetshop.presentation.basket.BasketPageViewModel
import org.mipt.planetshop.presentation.common.BaseFragment
import org.mipt.planetshop.presentation.common.setImageUrl

class PlanetDetailsFragment (
    val planet: Planet
): BaseFragment(R.layout.planet_details) {

    private val viewBinding by viewBinding(PlanetDetailsBinding::bind)
    private val viewModel: PlanetDetailsViewModel by viewModels() {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PlanetDetailsViewModel(
                    BasketRepositoryProvider.getRepository()
                ) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            planetDetailsTitle.text = planet.title
            planetDetailsExplanation.text = planet.explanation
            planetDetailsPicture.setImageUrl(planet.url)
        }

        viewBinding.planetDetailsBuyButton.setOnClickListener {
            viewModel.addPlanetToBasket(planet)
        }
    }

}