package com.annevonwolffen.behancer.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.annevonwolffen.behancer.R
import com.annevonwolffen.behancer.ui.common.PresenterFragment
import com.annevonwolffen.behancer.ui.common.RefreshOwner
import com.annevonwolffen.behancer.ui.common.Refreshable
import moxy.ktx.moxyPresenter

class ProjectsFragment : PresenterFragment(), ProjectsView, Refreshable {

    private lateinit var refreshOwner: RefreshOwner
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorView: TextView

    private val presenter by moxyPresenter { ProjectsPresenter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

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
//        recyclerView.adapter = ProjectsAdapter()
    }

    override fun onRefreshData() {
        presenter.getProjects()
    }

    override fun showProjects() {
        errorView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        // TODO: add items to adapter
    }

    override fun showLoading() {
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