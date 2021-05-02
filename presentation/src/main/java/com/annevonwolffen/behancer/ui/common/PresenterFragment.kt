package com.annevonwolffen.behancer.ui.common

import androidx.fragment.app.Fragment

/**
 * Базовый класс презентера
 */
abstract class PresenterFragment : Fragment() {

    protected abstract val presenter: BasePresenter

    override fun onDetach() {
        presenter.disposeAll()
        super.onDetach()
    }
}