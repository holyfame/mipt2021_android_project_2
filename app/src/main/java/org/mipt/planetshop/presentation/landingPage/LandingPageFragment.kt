package org.mipt.planetshop.presentation.landingPage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.LandingPageBinding
import org.mipt.planetshop.presentation.common.BaseFragment
import org.mipt.planetshop.presentation.common.navigate
import org.mipt.planetshop.presentation.planetsGallery.PlanetsGalleryFragment

class LandingPageFragment : BaseFragment(R.layout.landing_page) {

    private val viewBinding by viewBinding(LandingPageBinding::bind)
    private val viewModel by viewModels<LandingPageViewModel>()

    private var planetsGalleryFragment = PlanetsGalleryFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.landingPageIncreaseDate.setOnClickListener {
            viewModel.onAdd()
        }
        viewModel.dateState.observe(viewLifecycleOwner) { date ->
            viewBinding.landingPageSampleDate.text = date.toString()
        }
        viewBinding.landingPageShowGallery.setOnClickListener {
            showGallery()
        }
    }

    private fun showGallery() {
        parentFragmentManager.navigate(planetsGalleryFragment)
    }

}