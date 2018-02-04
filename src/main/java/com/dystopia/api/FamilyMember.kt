package com.dystopia.api

interface FamilyMember<in T> {
    infix fun sameAs(another: T): Boolean
}