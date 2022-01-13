package org.mipt.planetshop.di

import org.mipt.planetshop.data.BasketRepositoryImpl

object BasketRepositoryProvider {
    private var repository = BasketRepositoryImpl()

    fun getRepository(): BasketRepositoryImpl {
        return repository
    }
}