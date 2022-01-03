package org.mipt.planetshop.presentation.landingPage

import android.os.Bundle
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

//    private var planetsGalleryFragment = PlanetsGalleryFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.landingPageIncreaseCounter.setOnClickListener {
            viewModel.onAdd()
        }
        viewModel.counterState.observe(viewLifecycleOwner) { date ->
            viewBinding.landingPageSampleCounter.text = date.toString()
        }
        viewBinding.landingPageShowGallery.setOnClickListener {
            showGallery(
                viewBinding.landingPageStartDateEdit.text.toString(),
                viewBinding.landingPageEndDateEdit.text.toString()
            )
        }

        viewBinding.landingPageEndDateEdit.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                showGallery()
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
        viewModel.search(
            viewBinding.landingPageStartDateEdit.text.toString(),
            viewBinding.landingPageEndDateEdit.text.toString(),
        )
    }

    private fun showGallery(startDate: String, endDate: String) {
//        parentFragmentManager.navigate(PlanetsGalleryFragment.newInstance(startDate, endDate))
        parentFragmentManager.navigate(PlanetsGalleryFragment.newInstance("2021-10-15", "2021-10-25"))
    }

    fun DateState.getText(): String =
        when (this) {
            DateState.EMPTY -> "Необходимо заполнить поле"
            DateState.VALID -> ""
        }

}