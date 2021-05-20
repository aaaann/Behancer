package com.annevonwolffen.behancer.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.annevonwolffen.behancer.ui.common.BaseViewModel
import com.annevonwolffen.behancer.utils.SchedulersProvider
import com.annevonwolffen.domain.Project
import com.annevonwolffen.domain.ProjectsInteractor

class ProjectsViewModel(
    private val interactor: ProjectsInteractor,
    private val schedulersProvider: SchedulersProvider
) : BaseViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError
    private val _projects = MutableLiveData<List<Project>>()
    val projects: LiveData<List<Project>> = _projects

    fun loadProjects() {
        interactor.getProjects()
            .subscribeOn(schedulersProvider.io())
            .doOnSubscribe { _isLoading.value = true }
            .observeOn(schedulersProvider.ui())
            .doFinally { _isLoading.value = false }
            .doOnError { _isError.value = true }
            .subscribe(
                {
                    if (it.isEmpty()) _isError.value = true
                    else _projects.value = it
                },
                { Log.d(TAG, it.message.orEmpty()) }
            ).also { compositeDisposable.add(it) }
    }

    private companion object {
        const val TAG = "ProjectsViewModel"
    }
}