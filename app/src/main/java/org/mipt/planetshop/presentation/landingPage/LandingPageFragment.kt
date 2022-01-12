package org.mipt.planetshop.presentation.landingPage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.LandingPageBinding
import org.mipt.planetshop.presentation.common.BaseFragment
import org.mipt.planetshop.presentation.common.navigate
import org.mipt.planetshop.presentation.planetsGallery.PlanetsGalleryFragment

@AndroidEntryPoint
class LandingPageFragment : BaseFragment(R.layout.landing_page) {

    private val viewBinding by viewBinding(LandingPageBinding::bind)
    private val viewModel by viewModels<LandingPageViewModel>()

    private var planetsGalleryFragment : PlanetsGalleryFragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.landingPageShowGallery.setOnClickListener {
            showGallery()
        }

        viewBinding.landingPageEndDateEdit.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                search()
            }
            true
        }

        viewModel.startDateState.observe(viewLifecycleOwner) {
            viewBinding.landingPageStartDateLayout.error = it.getText()
        }

        viewModel.endDateState.observe(viewLifecycleOwner) {
            viewBinding.landingPageEndDateLayout.error = it.getText()
        }
    }

    private fun search() {
        var startDate = viewBinding.landingPageStartDateEdit.text.toString()
        if (startDate == "") {
            startDate = viewBinding.landingPageStartDateLayout.placeholderText.toString()
        }

        var endDate = viewBinding.landingPageEndDateEdit.text.toString()
        if (endDate == "") {
            endDate = viewBinding.landingPageEndDateLayout.placeholderText.toString()
        }

        viewModel.search(startDate, endDate)
        planetsGalleryFragment = PlanetsGalleryFragment.newInstance(startDate, endDate)
        showGallery()
    }

    private fun showGallery() {
        if (planetsGalleryFragment == null) {
            search()
            return
        }
        parentFragmentManager.navigate(planetsGalleryFragment!!)
    }

    private fun DateState.getText(): String =
        when (this) {
            DateState.EMPTY -> "Необходимо заполнить поле"
            DateState.VALID -> ""
        }

}