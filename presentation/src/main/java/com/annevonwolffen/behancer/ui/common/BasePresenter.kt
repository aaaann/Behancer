package com.annevonwolffen.behancer.ui.common

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter {

    protected val compositeDisposable = CompositeDisposable()

    fun disposeAll() {
        compositeDisposable.clear()
    }
}