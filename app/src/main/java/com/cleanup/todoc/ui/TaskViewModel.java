package com.cleanup.todoc.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // REPOSITORIES
    private final TaskDataRepository taskDataSource;
    private final Executor executor;

    // CONSTRUCTOR

    public TaskViewModel(TaskDataRepository taskDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.executor = executor;
    }

    //--- TASKS LIST ---
    public LiveData<List<Task>> getTasks() {
        return taskDataSource.getTasks();
    }

    // --- CREATE ---
    public void createTask(Task task) { executor.execute(() -> taskDataSource.createTask(task)); }

    // --- DELETE ---
    public void deleteTask(Task task){ executor.execute(() -> taskDataSource.deleteTask(task)); }

    public LiveData<List<Task>> orderTaskByAsc(){return taskDataSource.orderTaskByAsc();}

    public LiveData<List<Task>> orderTaskByDesc(){return taskDataSource.orderTaskByDesc();}

    public LiveData<List<Task>> orderTaskByRecent(){return taskDataSource.orderTaskByRecent();}

    public LiveData<List<Task>> orderTaskByOlder(){return taskDataSource.orderTaskByOlder();}


}
