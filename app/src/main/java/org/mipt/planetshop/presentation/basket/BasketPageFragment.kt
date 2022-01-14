package org.mipt.planetshop.presentation.basket

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.mipt.planetshop.R
import org.mipt.planetshop.databinding.BasketPageBinding
import org.mipt.planetshop.di.BasketRepositoryProvider
import org.mipt.planetshop.domain.entity.Planet
import org.mipt.planetshop.presentation.common.BaseFragment
import org.mipt.planetshop.presentation.common.navigate

class BasketPageFragment: BaseFragment(R.layout.basket_page) {

    private val viewBinding by viewBinding(BasketPageBinding::bind)
    private val viewModel: BasketPageViewModel by viewModels() {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return BasketPageViewModel(
                    BasketRepositoryProvider.getRepository()
                ) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var basketAdapter = BasketPageAdapter(viewModel)

        with(viewBinding.basketPageList) {
            adapter = basketAdapter
            layoutManager = LinearLayoutManager(context)
        }

//        viewModel.basketState.observe(viewLifecycleOwner) {
//            Log.d("PIZDEC", "MAFRENDS")
            basketAdapter.submitList(viewModel.basketState.value)
//            viewBinding.basketPageClearBasket.isVisible = true
//        }

        viewBinding.basketPageClearBasket.setOnClickListener {

            if (viewModel.basketState.value?.isEmpty() == false){
                viewModel.clearBasket()
                basketAdapter.submitList(viewModel.basketState.value)
                with(viewBinding.basketPageList) {
                    adapter = basketAdapter
                    layoutManager = LinearLayoutManager(context)
                }

                viewBinding.basketPageClearBasket.isVisible = false
            }
//            parentFragmentManager.navigate(BasketPageFragment())

        }

        fun submPlanets(plans : List<Planet>, basketAd : BasketPageAdapter) {
            with(viewBinding.basketPageList) {
                adapter = basketAd
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}