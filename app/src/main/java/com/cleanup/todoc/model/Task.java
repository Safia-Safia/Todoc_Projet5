package com.cleanup.todoc.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * <p>Model for the tasks of the application.</p>
 *
 * @author Gaëtan HERFRAY
 */
@Entity(
        tableName = "Task",
        foreignKeys = {@ForeignKey(entity = Project.class,
                parentColumns = "projectId",
                childColumns = "projectOwnerId",
                onDelete = ForeignKey.CASCADE)
        }
)
public class Task {
    /**
     * The unique identifier of the task
     */
    @PrimaryKey
    public long taskId;

    /**
     * The unique identifier of the project associated to the task
     */
    @ColumnInfo(name = "projectOwnerId", index = true)
    public long projectOwnerId;

    /**
     * The name of the task
     */
    private String taskName;

    /**
     * The timestamp when the task has been created
     */
    private long creationTimestamp;

    public Task(){}
    /**
     * Instantiates a new Task.
     *
     * @param taskId            the unique identifier of the task to set
     * @param projectOwnerId    the unique identifier of the project associated to the task to set
     * @param name              the name of the task to set
     * @param creationTimestamp the timestamp when the task has been created to set
     */
    public Task(long taskId, long projectOwnerId, @NonNull String name, long creationTimestamp) {
        this.setTaskId(taskId);
        this.setProjectOwnerId(projectOwnerId);
        this.setTaskName(name);
        this.setCreationTimestamp(creationTimestamp);
    }


    /**
     * Sets the unique identifier of the task.
     *
     * @param taskId the unique idenifier of the task to set
     */
    private void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    /**
     * Sets the unique identifier of the project associated to the task.
     *
     * @param projectOwnerId the unique identifier of the project associated to the task to set
     */
    private void setProjectOwnerId(long projectOwnerId) {
        this.projectOwnerId = projectOwnerId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
