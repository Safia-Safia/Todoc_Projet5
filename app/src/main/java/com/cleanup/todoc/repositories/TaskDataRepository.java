package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

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
    public LiveData<List<Task>> getTasks() {
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

    public LiveData<List<Task>> orderTaskByAsc(){return this.mTaskDao.orderTaskByAsc();}

    public LiveData<List<Task>> orderTaskByDesc(){return this.mTaskDao.orderTaskByDesc();}

    public LiveData<List<Task>> orderTaskByRecent(){return this.mTaskDao.orderTaskByRecent();}

    public LiveData<List<Task>> orderTaskByOlder(){return this.mTaskDao.orderTaskByOlder();}

}
