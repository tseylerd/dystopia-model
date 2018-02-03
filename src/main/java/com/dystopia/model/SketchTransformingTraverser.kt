package com.dystopia.model

import com.dystopia.model.sketch.api.SketchAtom
import com.dystopia.model.sketch.api.SketchElement

interface SketchTransformingTraverser : PairTransformingTraverser<SketchAtom, SketchElement>