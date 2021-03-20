package com.cleanup.todoc.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskAndProject;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // REPOSITORIES
    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataRepository;
    private final Executor executor;

    // CONSTRUCTOR

    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataRepository, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataRepository = projectDataRepository;
        this.executor = executor;
    }

    public LiveData<List<Project>> getProjects() {
        return projectDataRepository.getProjects();
    }


    //--- TASKS LIST ---
    public LiveData<List<TaskAndProject>> getTasks() {
        return taskDataSource.getTasks();
    }

    // --- CREATE ---
    public void createTask(Task task) { executor.execute(() -> taskDataSource.createTask(task)); }

    // --- DELETE ---
    public void deleteTask(Task task){ executor.execute(() -> taskDataSource.deleteTask(task)); }

    public LiveData<List<TaskAndProject>> orderTaskByAsc(){return taskDataSource.orderTaskByAsc();}

    public LiveData<List<TaskAndProject>> orderTaskByDesc(){return taskDataSource.orderTaskByDesc();}

    public LiveData<List<TaskAndProject>> orderTaskByRecent(){return taskDataSource.orderTaskByRecent();}

    public LiveData<List<TaskAndProject>> orderTaskByOlder(){return taskDataSource.orderTaskByOlder();}


}
