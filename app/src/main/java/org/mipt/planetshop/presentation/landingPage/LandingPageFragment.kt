package org.mipt.planetshop.presentation.landingPage

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.LandingPageBinding
import org.mipt.planetshop.di.NetworkModule
import org.mipt.planetshop.presentation.common.BaseFragment
import org.mipt.planetshop.presentation.planetsGallery.PlanetsGalleryFragment

class LandingPageFragment : BaseFragment(R.layout.landing_page) {

    private val viewBinding by viewBinding(LandingPageBinding::bind)
    private val viewModel by viewModels<LandingPageViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T: ViewModel?> create(modelClass: Class<T>): T =
                LandingPageViewModel(NetworkModule.getRepository()) as T
        }
    }

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
        viewModel.planetList.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.joinToString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun showGallery() {
        val fragment = PlanetsGalleryFragment()
        parentFragmentManager.commit(allowStateLoss = true) {
            replace(R.id.main_activity_container, fragment)
            addToBackStack(null)
        }
    }

}