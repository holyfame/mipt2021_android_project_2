package org.mipt.planetshop.presentation.planetDetails

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.PlanetDetailsBinding
import org.mipt.planetshop.databinding.PlanetsGalleryBinding
import org.mipt.planetshop.domain.entity.Planet
import org.mipt.planetshop.presentation.common.BaseFragment
import org.mipt.planetshop.presentation.common.setImageUrl

class PlanetDetailsFragment (
    val planet: Planet
): BaseFragment(R.layout.planet_details) {

    private val viewBinding by viewBinding(PlanetDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            planetDetailsTitle.text = planet.title
            planetDetailsExplanation.text = planet.explanation
            planetDetailsPicture.setImageUrl(planet.url)
        }

        viewBinding.planetDetailsArrowBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

}