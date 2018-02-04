package com.dystopia.api

import com.dystopia.api.provider.MergeProvider

interface MergeAware<T>: FamilyMember<T> {
    fun merge(another: T, provider: MergeProvider<T>): T
}