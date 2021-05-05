package com.annevonwolffen.behancer.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.annevonwolffen.behancer.R
import com.annevonwolffen.behancer.utils.DateUtils
import com.annevonwolffen.domain.Project

class ProjectsAdapter : ListAdapter<Project, ProjectsAdapter.ViewHolder>(DiffUtilCallback()) {

    class DiffUtilCallback : DiffUtil.ItemCallback<Project>() {

        override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.project_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val coverImage: ImageView = view.findViewById(R.id.cover_image)
        private val projectNameTextView: TextView = view.findViewById(R.id.project_name)
        private val projectOwnerTextView: TextView = view.findViewById(R.id.project_owner)
        private val projectPublishDateTextView: TextView = view.findViewById(R.id.publish_date)

        fun bind(project: Project) {
            with(project) {
                projectNameTextView.text = name
                projectOwnerTextView.text =
                    if (ownerNames.size > 1) projectOwnerTextView.context.getString(R.string.few_owners) else ownerNames.firstOrNull()
                projectPublishDateTextView.text = DateUtils.formatLongToString(publishDate)
            }
        }
    }
}