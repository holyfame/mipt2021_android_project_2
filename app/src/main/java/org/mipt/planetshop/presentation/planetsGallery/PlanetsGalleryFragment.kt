package org.mipt.planetshop.presentation.planetsGallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.PlanetsGalleryBinding
import org.mipt.planetshop.domain.entity.Planet
import org.mipt.planetshop.presentation.common.BaseFragment
import org.mipt.planetshop.presentation.planetDetails.PlanetDetailsFragment

@AndroidEntryPoint
class PlanetsGalleryFragment : BaseFragment(R.layout.planets_gallery) {

    private val viewBinding by viewBinding(PlanetsGalleryBinding::bind)
    private val viewModel by viewModels<PlanetsGalleryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val planetsGalleryAdapter = PlanetsGalleryAdapter(viewModel::onPlanetClicked)
        with(viewBinding.planetList) {
            adapter = planetsGalleryAdapter
            layoutManager = LinearLayoutManager(context)
        }
//        viewBinding.planetsGalleryArrowBack.setOnClickListener {
//            parentFragmentManager.popBackStack()
//        }
        viewModel.openDetailAction.observe(viewLifecycleOwner) {
            openDetail(it)
        }
        viewModel.planetList.observe(viewLifecycleOwner) {
//            Toast.makeText(requireContext(), it.joinToString(), Toast.LENGTH_LONG).show()
            planetsGalleryAdapter.submitList(it)
        }
    }

    private fun openDetail(planet: Planet) {
        parentFragmentManager.commit(allowStateLoss = true) {
            replace(R.id.main_activity_container, PlanetDetailsFragment(planet))
            addToBackStack(null)
        }
    }

}