package com.dystopia.api


interface Element<T: Element<T>>: FamilyMember<T> {
    val children: Array<T>
    val pairTraversal: PairTraversal<T>
}