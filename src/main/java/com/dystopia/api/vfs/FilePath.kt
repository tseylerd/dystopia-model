package com.dystopia.api.vfs

class FilePath {
    val path = ArrayList<VirtualDirectory>()

    fun inDirectory(directory: VirtualDirectory, callback: (Unit) -> Boolean): Boolean {
        path.add(directory)
        if (callback.invoke(Unit)) return true
        path.remove(directory)
        return false
    }
}