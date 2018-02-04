package com.dystopia.api

interface Atom<T>: DiffAware<T>, MergeAware<T> where T : Element<T>
