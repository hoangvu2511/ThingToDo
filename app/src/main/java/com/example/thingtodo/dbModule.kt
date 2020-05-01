package com.example.thingtodo

import com.example.thingtodo.feature.CreateTaskFragment
import com.example.thingtodo.viewmodel.MainActivityViewModel
import com.example.thingtodo.viewmodel.TaskTypeViewModel
import com.example.thingtodo.viewmodel.TaskViewModel
import com.vunguyenhoang.core.database.TodoDb
import com.vunguyenhoang.core.repository.TaskRepository
import com.vunguyenhoang.core.repository.TaskRepositoryIml
import com.vunguyenhoang.core.repository.taskType.TaskTypeRepo
import com.vunguyenhoang.core.repository.taskType.TaskTypeRepoIml
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {
    single { TodoDb.getDb(androidApplication()) }
    single { get<TodoDb>().taskDao() }
    single { get<TodoDb>().taskTypeDao() }

    single { CreateTaskFragment() }

    factory<TaskRepository> { TaskRepositoryIml(get()) }

    single<TaskTypeRepo> { TaskTypeRepoIml(get()) }

    viewModel { TaskViewModel(get()) }
    viewModel { TaskTypeViewModel(get()) }

    single { MainActivityViewModel() }
}