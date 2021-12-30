package org.mipt.planetshop.presentation.planetsGallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.mipt.planetshop.databinding.PlanetItemBinding
import org.mipt.planetshop.domain.entity.Planet
import org.mipt.planetshop.presentation.common.setImageUrl

class PlanetsGalleryAdapter(
    private val onPlanetClicked: (Planet) -> Unit
) : ListAdapter<Planet, PlanetsGalleryAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Planet>() {
        override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean = oldItem == newItem

    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            PlanetItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val item = getItem(position)
            planetItemTitle.text = item.title
            planetItemExplanation.text = item.explanation
            planetItemPicture.setImageUrl(item.url)
            root.setOnClickListener { onPlanetClicked(item) }
        }
    }

    class ViewHolder(val binding: PlanetItemBinding) : RecyclerView.ViewHolder(binding.root)
}