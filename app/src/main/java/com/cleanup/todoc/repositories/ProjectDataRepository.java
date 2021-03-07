package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

public class ProjectDataRepository {

    private final ProjectDao mProjectDao;

    public ProjectDataRepository(ProjectDao projectDao) {
        mProjectDao = projectDao;
    }

    public LiveData<Project> getProject(long id) {
        return this.mProjectDao.getProject(id);
    }

    // --- CREATE ---

    public void createProject(Project project) {
        this.mProjectDao.createProject(project);
    }
}
