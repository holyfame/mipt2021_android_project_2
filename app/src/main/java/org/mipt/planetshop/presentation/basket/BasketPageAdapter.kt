package org.mipt.planetshop.presentation.basket

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.mipt.planetshop.data.BasketRepositoryImpl
import org.mipt.planetshop.databinding.BasketItemBinding
import org.mipt.planetshop.databinding.PlanetItemBinding
import org.mipt.planetshop.di.BasketRepositoryProvider
import org.mipt.planetshop.domain.BasketRepository
import org.mipt.planetshop.domain.entity.Planet
import org.mipt.planetshop.presentation.common.setImageUrl
import org.mipt.planetshop.presentation.planetDetails.PlanetDetailsViewModel

class BasketPageAdapter(val viewModel : BasketPageViewModel) : ListAdapter<Planet, BasketPageAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Planet>() {
        override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Planet, newItem: Planet):
                Boolean = oldItem == newItem
    }
) {
    var removedPosition : Int ? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            BasketItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.binding) {
            val item = getItem(position)
            basketItemTitle.text = item.title
            basketItemPicture.setImageUrl(item.url)

//            root.setOnClickListener { onPlanetClicked(item) }
            delButton.setOnClickListener{
                Log.d("SIZE BEFORE 1", "${viewModel.basketState.value?.size}")
                viewModel.remBasketItem(position)
//                viewModel.clearBasket()
                Log.d("SIZE AFTER 2", "${viewModel.basketState.value?.size}")
                notifyDataSetChanged()


//                currentList.removeAt(position)
//                notifyItemRemoved(position)
//                notifyItemRangeChanged(position,currentList.size)
//                Log.d("11111111", "$currentList")
                Log.d("22222222", "$item")
                Log.d("33333333", "${currentList.size}")
                Log.d("44444444", "$position")
            }
        }
    }



    class ViewHolder(val binding: BasketItemBinding) : RecyclerView.ViewHolder(binding.root)
}