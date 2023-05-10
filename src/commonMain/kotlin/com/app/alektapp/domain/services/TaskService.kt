package com.app.alektapp.domain.services

import com.app.alektapp.domain.model.TaskUnit

interface TaskService {
    fun isPassedSuccess(): Boolean
    fun passed(): Set<TaskUnit>
    fun failed(): Set<TaskUnit>
}