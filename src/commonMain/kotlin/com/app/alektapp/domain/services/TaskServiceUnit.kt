package com.app.alektapp.domain.services

import com.app.alektapp.domain.model.TaskUnit

open class TaskServiceUnit(val data: Set<TaskUnit>): TaskService {


   override fun isPassedSuccess(): Boolean {
        data.toList().find { task ->
            task.chosenAnswer != task.rightAnswer
        }?.let {
            return@isPassedSuccess false
        }

        return true
    }
     override fun passed(): Set<TaskUnit> = data.filter { task ->
            task.chosenAnswer == task.rightAnswer
        }.toSet()

     override fun failed(): Set<TaskUnit> = data.filter { task ->
        task.chosenAnswer != task.rightAnswer
    }.toSet()
}