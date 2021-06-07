package com.example.abdl.academy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.abdl.academy.data.source.local.entity.CourseEntity
import com.example.abdl.academy.data.source.AcademyRepository

class BookmarkViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    fun getBookmarks(): LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCources()
}