package org.mipt.planetshop.presentation.planetsGallery

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.PlanetsGalleryBinding
import org.mipt.planetshop.domain.entity.Planet
import org.mipt.planetshop.presentation.common.BaseFragment
import org.mipt.planetshop.presentation.common.navigate
import org.mipt.planetshop.presentation.planetDetails.PlanetDetailsFragment
import javax.inject.Inject

@AndroidEntryPoint
class PlanetsGalleryFragment : BaseFragment(R.layout.planets_gallery) {

    companion object {
        fun newInstance(startDate: String, endDate: String) = PlanetsGalleryFragment().apply {
            arguments = bundleOf(START_DATE_KEY to startDate, END_DATE_KEY to endDate)
        }

        const val START_DATE_KEY = "START_DATE_KEY"
        const val END_DATE_KEY = "END_DATE_KEY"
    }

    @Inject lateinit var planetsGalleryViewModelFactory: PlanetsGalleryViewModel.Factory

    private val viewBinding by viewBinding(PlanetsGalleryBinding::bind)

    private val viewModel by viewModels<PlanetsGalleryViewModel>{
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                planetsGalleryViewModelFactory.create(
                    startDate = arguments?.getString(START_DATE_KEY) ?: "",
                    endDate = arguments?.getString(END_DATE_KEY) ?: ""
                ) as T
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val planetsGalleryAdapter = PlanetsGalleryAdapter(viewModel::onPlanetClicked)

        with(viewBinding.planetsGalleryList) {
            adapter = planetsGalleryAdapter
//            layoutManager = LinearLayoutManager(context)
            layoutManager = GridLayoutManager(context, 2)
        }

        viewModel.openDetailAction.observe(viewLifecycleOwner) {
            openDetail(it)
        }
        viewModel.planetsGalleryState.observe(viewLifecycleOwner) { state: PlanetsGalleryState ->
            when (state) {
                is PlanetsGalleryState.Error -> {
                    viewBinding.planetsGalleryError.isVisible = true
                    viewBinding.planetsGalleryProgress.isVisible = false
                    viewBinding.planetsGalleryList.isVisible = false
                }
                is PlanetsGalleryState.Loading -> {
                    viewBinding.planetsGalleryError.isVisible = false
                    viewBinding.planetsGalleryProgress.isVisible = true
                    viewBinding.planetsGalleryList.isVisible = false
                }
                is PlanetsGalleryState.Success -> {
                    viewBinding.planetsGalleryError.isVisible = false
                    viewBinding.planetsGalleryProgress.isVisible = false
                    viewBinding.planetsGalleryList.isVisible = true
                    planetsGalleryAdapter.submitList(state.planets)
                }
            }
        }
    }

    private fun openDetail(planet: Planet) {
        parentFragmentManager.navigate(PlanetDetailsFragment(planet))
    }

}