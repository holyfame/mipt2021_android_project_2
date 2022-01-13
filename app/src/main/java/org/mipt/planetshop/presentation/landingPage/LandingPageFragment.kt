package org.mipt.planetshop.presentation.landingPage

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.LandingPageBinding
import org.mipt.planetshop.presentation.common.BaseFragment
import org.mipt.planetshop.presentation.common.navigate
import org.mipt.planetshop.presentation.landingPage.DateState.*
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
            if (actionId == EditorInfo.IME_ACTION_DONE ) {
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

    var dateStateValid:Boolean = true;

    private fun search() {
        val startDate = viewBinding.landingPageStartDateEdit.text.toString()

        val endDate = viewBinding.landingPageEndDateEdit.text.toString()

        dateStateValid = viewModel.searchValidation(startDate, endDate)
        if (dateStateValid) {
            planetsGalleryFragment = PlanetsGalleryFragment.newInstance(startDate, endDate)
            showGallery()
        }
    }

    private fun showGallery() {
        if (!dateStateValid)
            return
        if (planetsGalleryFragment == null) {
            search()
            return
        }

        //прячем клавиатуру
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)

        parentFragmentManager.navigate(planetsGalleryFragment!!)
    }

    private fun DateState.getText(): String =
        when (this) {
            EMPTY -> "Необходимо заполнить поле"
            VALID -> ""
            ERROR_BETWEEN -> "Слишком много планет. Введите диапозон менее 3 месяцев"
            ERROR_CHRONOLOGY -> "Конечная дата больше начальной"
            ERROR_FORMAT -> "Неправильный формат даты. (2022-01-01)"
            ERROR_FUTURE_DATE -> "Время еще не наступило"
            ERROR_TOOEARLY -> "Введите дату позднее 2015 года"
        }


}