package com.dystopia.model

import com.dystopia.model.sketch.api.FamilyMember


interface Element<T: Element<T>>: FamilyMember<T> {
    val children: Array<T>
    val pairTraversal: PairTraversal<T>
}