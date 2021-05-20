package com.annevonwolffen.behancer.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.annevonwolffen.behancer.AppDelegate.Companion.APP_SCOPE
import com.annevonwolffen.behancer.R
import com.annevonwolffen.behancer.databinding.ProjectsBinding
import com.annevonwolffen.behancer.ui.common.RefreshOwner
import com.annevonwolffen.behancer.ui.common.Refreshable
import com.annevonwolffen.behancer.utils.SchedulersProvider
import com.annevonwolffen.domain.ProjectsInteractor
import toothpick.ktp.KTP
import toothpick.ktp.delegate.lazy

class ProjectsFragment : Fragment(), Refreshable {

    private lateinit var refreshOwner: RefreshOwner
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: ProjectsAdapter
    private lateinit var errorView: TextView

    private lateinit var projectsViewModel: ProjectsViewModel

    val interactor: ProjectsInteractor by lazy()
    val schedulersProvider: SchedulersProvider by lazy()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        KTP.openScope(APP_SCOPE).inject(this)

        if (context is RefreshOwner) {
            refreshOwner = context
        }
        projectsViewModel = ProjectsViewModel(
            interactor,
            schedulersProvider
        ) // TODO: get viewmodel with help of ViewModelProvider
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ProjectsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fr_projects, container, false)
        binding.viewModel = projectsViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.projects_recycler)
        errorView = view.findViewById(R.id.error_view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.title = getString(R.string.projects_title)

        setUpRecycler()
        initObservers()

        if (savedInstanceState == null) {
            onRefreshData()
        }
    }

    private fun setUpRecycler() {
        recyclerView.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
        recyclerViewAdapter = ProjectsAdapter()
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun initObservers() {
        projectsViewModel.isLoading.observe(this, Observer { loading ->
            refreshOwner.setRefreshState(loading)
        })
    }

    override fun onRefreshData() {
        projectsViewModel.loadProjects()
    }

    companion object {
        fun newInstance() = ProjectsFragment()
    }
}