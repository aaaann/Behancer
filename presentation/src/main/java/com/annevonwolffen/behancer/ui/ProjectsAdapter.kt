package com.annevonwolffen.behancer.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.annevonwolffen.behancer.R
import com.annevonwolffen.behancer.databinding.ProjectsListItemBinding
import com.annevonwolffen.domain.Project

class ProjectsAdapter : ListAdapter<Project, ProjectsAdapter.ViewHolder>(DiffUtilCallback()) {

    class DiffUtilCallback : DiffUtil.ItemCallback<Project>() {

        override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ProjectsListItemBinding>(inflater, R.layout.project_list_item, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(project: Project) {
            val binding = DataBindingUtil.getBinding<ProjectsListItemBinding>(view)
            binding?.let {
                it.project = project
                it.executePendingBindings()
            }
        }
    }
}