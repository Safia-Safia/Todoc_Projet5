package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createTask(Task task);

    @Query("SELECT * FROM Task WHERE id = :id")
    LiveData<Task> getTask(long id);

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getTasks();

    @Delete
    void deleteTask(Task task);

    @Query("SELECT * FROM Task ORDER BY name ASC ")
    LiveData<List<Task>> orderTaskByAsc();

    @Query("SELECT * FROM Task ORDER BY name DESC ")
    LiveData<List<Task>> orderTaskByDesc();

    @Query("SELECT * FROM Task ORDER BY creationTimestamp ASC ")
    LiveData<List<Task>> orderTaskByRecent();

    @Query("SELECT * FROM Task ORDER BY creationTimestamp DESC ")
    LiveData<List<Task>> orderTaskByOlder();

}
