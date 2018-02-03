package com.dystopia.model.provider

interface MergeProvider<E> {
    fun conflict(base: E, current: E): E
    fun zip(base: E, current: E): E
}