package com.example.abdl.academy.ui.bookmark

import com.example.abdl.academy.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
