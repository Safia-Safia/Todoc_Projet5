package com.cleanup.todoc.model;

import android.arch.persistence.room.Embedded;

public class TaskAndProject {
    @Embedded
    public Project mProject;
    @Embedded
    public Task mTask;
}
