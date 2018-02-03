package com.dystopia.model.sketch.api

interface FamilyMember<in T> {
    infix fun sameAs(another: T): Boolean
}