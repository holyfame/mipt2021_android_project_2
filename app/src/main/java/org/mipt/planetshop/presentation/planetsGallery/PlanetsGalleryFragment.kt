package org.mipt.planetshop.presentation.planetsGallery

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.PlanetsGalleryBinding
import org.mipt.planetshop.presentation.common.BaseFragment

class PlanetsGalleryFragment : BaseFragment(R.layout.planets_gallery) {

    private val viewBinding by viewBinding(PlanetsGalleryBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.planetsGalleryArrowBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }

}