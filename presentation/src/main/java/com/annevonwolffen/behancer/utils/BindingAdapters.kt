package com.annevonwolffen.behancer.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.annevonwolffen.behancer.ui.ProjectsAdapter
import com.annevonwolffen.domain.Project
import com.squareup.picasso.Picasso

@BindingAdapter("visibility")
fun View.setVisibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun ImageView.setImage(imageUrl: String) {
    Picasso.get()
        .load(imageUrl)
        .into(this)
}

@BindingAdapter("projectsData")
fun RecyclerView.setProjects(projects: List<Project>?) {
    if (adapter == null) {
        adapter = ProjectsAdapter()
    }

    projects?.takeIf { adapter is ProjectsAdapter }?.let {
        (adapter as ProjectsAdapter).submitList(projects)
    }
}