package com.dystopia.model

import com.dystopia.model.sketch.api.DiffAware
import com.dystopia.model.sketch.api.MergeAware

interface Atom<T>: DiffAware<T>, MergeAware<T> where T : Element<T>
