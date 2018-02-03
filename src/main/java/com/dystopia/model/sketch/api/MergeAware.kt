package com.dystopia.model.sketch.api

import com.dystopia.model.provider.MergeProvider

interface MergeAware<T>: FamilyMember<T> {
    fun merge(another: T, provider: MergeProvider<T>): T
}