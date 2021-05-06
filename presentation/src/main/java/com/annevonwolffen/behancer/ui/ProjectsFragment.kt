package com.annevonwolffen.behancer.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.annevonwolffen.behancer.AppDelegate
import com.annevonwolffen.behancer.R
import com.annevonwolffen.behancer.ui.common.PresenterFragment
import com.annevonwolffen.behancer.ui.common.RefreshOwner
import com.annevonwolffen.behancer.ui.common.Refreshable
import com.annevonwolffen.domain.Project
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class ProjectsFragment : PresenterFragment(), ProjectsView, Refreshable {

    private lateinit var refreshOwner: RefreshOwner
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: ProjectsAdapter
    private lateinit var errorView: TextView

    @Inject
    lateinit var presenterProvider: Provider<ProjectsPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as AppDelegate).appComponent.inject(this)

        if (context is RefreshOwner) {
            refreshOwner = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fr_projects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.projects_recycler)
        errorView = view.findViewById(R.id.error_view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.title = getString(R.string.projects_title)

        setUpRecycler()

        if (savedInstanceState == null) {
            onRefreshData()
        }
    }

    private fun setUpRecycler() {
        recyclerView.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
        recyclerViewAdapter = ProjectsAdapter()
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun onRefreshData() {
        presenter.getProjects()
    }

    override fun showProjects(projects: List<Project>) {
        errorView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        recyclerViewAdapter.submitList(projects)

    }

    override fun showLoading() {
        errorView.visibility = View.GONE
        recyclerView.visibility = View.GONE
        refreshOwner.setRefreshState(true)
    }

    override fun hideLoading() {
        refreshOwner.setRefreshState(false)
    }

    override fun showError() {
        errorView.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    companion object {
        fun newInstance() = ProjectsFragment()
    }
}