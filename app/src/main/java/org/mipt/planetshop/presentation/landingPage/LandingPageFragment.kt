package org.mipt.planetshop.presentation.landingPage

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.LandingPageBinding
import org.mipt.planetshop.presentation.common.BaseFragment

class LandingPageFragment : BaseFragment(R.layout.landing_page) {

    private val viewBinding by viewBinding(LandingPageBinding::bind)

    private var count: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.landingPageIncreaseDate.setOnClickListener {
            count ++
            setTextToCounter()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNTER_SAVE_KEY, count)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getInt(COUNTER_SAVE_KEY)?.let {
            count = it
            setTextToCounter()
        }
    }

    private fun setTextToCounter() {
        viewBinding.landingPageSampleDate.text = count.toString()
    }

    companion object {
        private const val COUNTER_SAVE_KEY = "COUNTER_SAVE_KEY"
    }

}