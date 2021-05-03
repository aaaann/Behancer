package com.annevonwolffen.behancer.ui.common

import moxy.MvpView

/**
 * Интерфейс базового экрана
 */
interface BaseView : MvpView {
    fun showLoading()

    fun hideLoading()

    fun showError()
}