package org.mipt.planetshop.presentation.planetsGallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.PlanetsGalleryBinding
import org.mipt.planetshop.di.NetworkModule
import org.mipt.planetshop.presentation.common.BaseFragment

class PlanetsGalleryFragment : BaseFragment(R.layout.planets_gallery) {

    private val viewBinding by viewBinding(PlanetsGalleryBinding::bind)
    private val viewModel by viewModels<PlanetsGalleryViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T: ViewModel?> create(modelClass: Class<T>): T =
                PlanetsGalleryViewModel(NetworkModule.getRepository()) as T
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val planetsGalleryAdapter = PlanetsGalleryAdapter()
        with(viewBinding.planetList) {
            adapter = planetsGalleryAdapter
            layoutManager = LinearLayoutManager(context)
        }
//        viewBinding.planetsGalleryArrowBack.setOnClickListener {
//            parentFragmentManager.popBackStack()
//        }
        viewModel.planetList.observe(viewLifecycleOwner) {
//            Toast.makeText(requireContext(), it.joinToString(), Toast.LENGTH_LONG).show()
            planetsGalleryAdapter.submitList(it)
        }
    }

}