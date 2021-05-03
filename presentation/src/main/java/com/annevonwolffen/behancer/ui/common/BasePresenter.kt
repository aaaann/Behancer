package com.annevonwolffen.behancer.ui.common

import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

open class BasePresenter<V : BaseView> : MvpPresenter<V>() {

    protected val compositeDisposable = CompositeDisposable()

    private fun disposeAll() {
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        disposeAll()
        super.onDestroy()
    }
}