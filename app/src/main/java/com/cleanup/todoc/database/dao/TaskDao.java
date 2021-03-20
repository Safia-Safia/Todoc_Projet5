package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.cleanup.todoc.model.TaskAndProject;
import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createTask(Task task);

    @Query("SELECT * FROM Task WHERE taskId = :id")
    LiveData<Task> getTask(long id);

    @Transaction
    @Query("SELECT * FROM Task JOIN Project ON projectId = projectOwnerId")
    LiveData<List<TaskAndProject>> getTasks();

    @Delete
    void deleteTask(Task task);

    @Query("SELECT * FROM Task JOIN Project ON projectId = projectOwnerId ORDER BY taskName ASC ")
    LiveData<List<TaskAndProject>> orderTaskByAsc();

    @Query("SELECT * FROM Task JOIN Project ON projectId = projectOwnerId ORDER BY taskName DESC ")
    LiveData<List<TaskAndProject>> orderTaskByDesc();

    @Query("SELECT * FROM Task JOIN Project ON projectId = projectOwnerId ORDER BY creationTimestamp ASC ")
    LiveData<List<TaskAndProject>> orderTaskByRecent();

    @Query("SELECT * FROM Task JOIN Project ON projectId = projectOwnerId ORDER BY creationTimestamp DESC ")
    LiveData<List<TaskAndProject>> orderTaskByOlder();

}
