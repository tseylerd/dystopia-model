package com.dystopia.model.sketch.api

import com.dystopia.model.provider.DiffProvider

interface DiffAware<T>: FamilyMember<T> {
    fun diff(another: T, provider: DiffProvider<T>): T
}