package com.nits.imagefilter.listeners

import java.io.File

interface SavedImageListener {
    fun onImageClicked(file: File)
}