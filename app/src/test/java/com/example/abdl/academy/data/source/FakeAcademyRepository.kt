package com.example.abdl.academy.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abdl.academy.data.source.local.entity.ContentEntity
import com.example.abdl.academy.data.source.local.entity.CourseEntity
import com.example.abdl.academy.data.source.local.entity.ModuleEntity
import com.example.abdl.academy.data.source.remote.response.ContentResponse
import com.example.abdl.academy.data.source.remote.response.CourseResponse
import com.example.abdl.academy.data.source.remote.response.ModuleResponse
import com.example.abdl.academy.data.source.remote.response.RemoteDataSource

class FakeAcademyRepository (private val remoteDataSource: RemoteDataSource): AcademyDataSource{

    override fun getAllCourses(): LiveData<List<CourseEntity>> {
        val courseResults = MutableLiveData<List<CourseEntity>>()
        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponse: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (response in courseResponse) {
                    val course = CourseEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath
                    )
                    courseList.add(course)
                }
                courseResults.postValue(courseList)
            }
        })
        return courseResults
    }

    override fun getBookmarkedCources(): MutableLiveData<List<CourseEntity>> {
        val courseResults = MutableLiveData<List<CourseEntity>>()

        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponse: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (response in courseResponse) {
                    val course = CourseEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath
                    )
                    courseList.add(course)
                }
                courseResults.postValue(courseList)
            }
        })
        return courseResults
    }

    override fun getCourseWithModules(courseId: String): LiveData<CourseEntity> {
        val courseResults = MutableLiveData<CourseEntity>()

        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponse: List<CourseResponse>) {
                lateinit var course: CourseEntity
                for (response in courseResponse) {
                    if (response.id == courseId) {
                        course = CourseEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.date,
                            false,
                            response.imagePath
                        )
                    }
                }
                courseResults.postValue(course)
            }
        })
        return courseResults
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<ArrayList<ModuleEntity>> {
        val moduleResults = MutableLiveData<ArrayList<ModuleEntity>>()

        remoteDataSource.getModules(courseId, object : RemoteDataSource.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponse: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in moduleResponse) {
                    val course = ModuleEntity(
                        response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false
                    )
                    moduleList.add(course)
                }
                moduleResults.postValue(moduleList)
            }
        })
        return moduleResults
    }

    override fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity> {
        val moduleResult = MutableLiveData<ModuleEntity>()

        remoteDataSource.getModules(courseId, object : RemoteDataSource.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponse: List<ModuleResponse>) {
                lateinit var module: ModuleEntity
                for (response in moduleResponse) {
                    if (response.moduleId == moduleId) {
                        module = ModuleEntity(
                            response.moduleId,
                            response.courseId,
                            response.title,
                            response.position,
                            false
                        )
                        remoteDataSource.getContent(
                            moduleId,
                            object : RemoteDataSource.LoadContentCallback {
                                override fun onContentReceived(contentResponse: ContentResponse) {
                                    module.contentEntity = ContentEntity(contentResponse.content)
                                    moduleResult.postValue(module)
                                }
                            })
                        break
                    }
                }
            }
        })
        return moduleResult
    }
}