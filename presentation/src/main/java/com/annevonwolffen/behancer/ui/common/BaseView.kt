package com.annevonwolffen.behancer.ui.common

/**
 * Интерфейс базового экрана
 */
interface BaseView {
    fun showLoading();

    fun hideLoading();

    fun showError();
}