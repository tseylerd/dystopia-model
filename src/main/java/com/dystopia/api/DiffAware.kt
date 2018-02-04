package com.dystopia.api

import com.dystopia.api.provider.DiffProvider

interface DiffAware<T>: FamilyMember<T> {
    fun diff(another: T, provider: DiffProvider<T>): T
}