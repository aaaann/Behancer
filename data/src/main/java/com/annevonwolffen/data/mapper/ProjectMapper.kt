package com.annevonwolffen.data.mapper

import com.annevonwolffen.data.model.Project

class ProjectMapper : DataToDomainMapper<Project, com.annevonwolffen.domain.Project> {
    override fun map(from: Project): com.annevonwolffen.domain.Project {
        return with(from) {
            com.annevonwolffen.domain.Project(
                id,
                name,
                cover.url,
                owners.map { it.displayName },
                publishedOn
            )
        }
    }
}