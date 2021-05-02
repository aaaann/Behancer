package com.annevonwolffen.behancer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.annevonwolffen.behancer.ProjectsAdapter
import com.annevonwolffen.behancer.R
import com.annevonwolffen.behancer.ui.common.Refreshable

class ProjectsFragment : Fragment(), Refreshable {

    private lateinit var recyclerView: RecyclerView
    private lateinit var errorView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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

    override fun onRefreshData() {
        getProjects()
    }

    private fun setUpRecycler() {
        recyclerView.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
//        recyclerView.adapter = ProjectsAdapter()
    }

    private fun getProjects() {
    }

    companion object {
        fun newInstance() = ProjectsFragment()
    }
}