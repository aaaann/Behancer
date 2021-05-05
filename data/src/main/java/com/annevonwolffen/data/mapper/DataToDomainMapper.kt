package com.annevonwolffen.data.mapper

/**
 * @param F тип
 * @param T
 */
interface DataToDomainMapper<F, T> {

    fun map(from: F): T

    fun mapList(from: List<F>): List<T> {
        return from.map { map(it) }
    }
}