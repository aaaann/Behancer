package com.annevonwolffen.domain

data class Project(
    val id: Int,
    val name: String,
    val coverUrl: String,
    val ownerNames: List<String>,
    val publishDate: Long
)