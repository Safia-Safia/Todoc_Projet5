package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskAndProject;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao mTaskDao;

    public TaskDataRepository(TaskDao taskDao) {
        mTaskDao = taskDao;
    }

    public LiveData<Task> getTask(long id) {
        return this.mTaskDao.getTask(id);
    }

    //--- TASKS LIST ---
    public LiveData<List<TaskAndProject>> getTasks() {
        return this.mTaskDao.getTasks();
    }

    //--- CREATE TASK ---
    public void createTask(Task task) {
        this.mTaskDao.createTask(task);
    }

    // --- DELETE ---
    public void deleteTask(Task task){
        this.mTaskDao.deleteTask(task);
    }

    //--- ORDER TASKS ---

    public LiveData<List<TaskAndProject>> orderTaskByAsc(){return this.mTaskDao.orderTaskByAsc();}

    public LiveData<List<TaskAndProject>> orderTaskByDesc(){return this.mTaskDao.orderTaskByDesc();}

    public LiveData<List<TaskAndProject>> orderTaskByRecent(){return this.mTaskDao.orderTaskByRecent();}

    public LiveData<List<TaskAndProject>> orderTaskByOlder(){return this.mTaskDao.orderTaskByOlder();}

}
