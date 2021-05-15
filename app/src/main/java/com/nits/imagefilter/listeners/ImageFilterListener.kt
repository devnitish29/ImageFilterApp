package com.nits.imagefilter.listeners

import com.nits.imagefilter.data.ImageFilter

interface ImageFilterListener {
    fun onFilterSelected(imageFilter: ImageFilter)
}