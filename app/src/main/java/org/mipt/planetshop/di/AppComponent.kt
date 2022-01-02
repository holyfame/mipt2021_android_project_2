package org.mipt.planetshop.di

import dagger.Component
import org.mipt.planetshop.presentation.planetsGallery.PlanetsGalleryFragment

@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(planetsGalleryFragment: PlanetsGalleryFragment)

}