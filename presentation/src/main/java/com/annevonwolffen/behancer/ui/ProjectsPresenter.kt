package com.annevonwolffen.behancer.ui

import android.util.Log
import com.annevonwolffen.behancer.ui.common.BasePresenter
import com.annevonwolffen.behancer.utils.SchedulersProvider
import com.annevonwolffen.domain.ProjectsInteractor
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class ProjectsPresenter @Inject constructor(
    private val interactor: ProjectsInteractor,
    private val schedulersProvider: SchedulersProvider
) :
    BasePresenter<ProjectsView>() {

    fun getProjects() {
        interactor.getProjects()
            .subscribeOn(schedulersProvider.io())
            .doOnSubscribe { viewState.showLoading() }
            .observeOn(schedulersProvider.ui())
            .doFinally { viewState.hideLoading() }
            .doOnError { viewState.showError() }
            .subscribe(
                {
                    if (it.isEmpty()) viewState.showError()
                    else viewState.showProjects(it)
                },
                { Log.d(TAG, it.message.orEmpty()) }
            ).also { compositeDisposable.add(it) }
    }

    private companion object {
        const val TAG = "ProjectsPresenter"
    }
}