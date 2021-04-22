package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskAndProject;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.ui.TaskViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.concurrent.Executor;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertSame;

/**
 * Unit tests for tasks
 *
 * @author Gaëtan HERFRAY
 */
@RunWith(JUnit4.class)
public class TaskUnitTest {

    @Mock
    private TaskDataRepository taskDataRepository;
    @Mock
    private ProjectDataRepository projectDataRepository;
    @Mock
    Executor executor;
    private TaskViewModel taskViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final Task task1 = new Task(1, 1, "aaa", 123);
    private final Task task2 = new Task(2, 2, "zzz", 124);
    private final Task task3 = new Task(3, 3, "hhh", 125);
    private final Project project1 = new Project(1, "Project Tartampion", 0xFFEADAD1);
    private final Project project2 = new Project(1, "Project Lucidia", 0xFFB4CDBA);
    private final TaskAndProject taskAndProject1 = new TaskAndProject();
    private final TaskAndProject taskAndProject2 = new TaskAndProject();
    private final TaskAndProject taskAndProject3 = new TaskAndProject();


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        taskViewModel = new TaskViewModel(taskDataRepository, projectDataRepository, executor);
        taskAndProject1.mTask = task1;
        taskAndProject2.mTask = task2;
        taskAndProject3.mTask = task3;
    }

    @Test
    public void getProjects() {
        MutableLiveData<List<Project>> result = new MutableLiveData<>();
        final ArrayList<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        result.setValue(projects);
        Mockito.when(projectDataRepository.getProjects()).thenReturn(result);
        taskViewModel.getProjects().observeForever(projects1 -> {
            assertEquals(projects, projects1);
        });
    }

    @Test
    public void getTasks() {
        MutableLiveData<List<TaskAndProject>> result = new MutableLiveData<>();
        final ArrayList<TaskAndProject> tasks = new ArrayList<>();
        tasks.add(taskAndProject1);
        tasks.add(taskAndProject2);
        tasks.add(taskAndProject3);
        result.setValue(tasks);
        Mockito.when(taskDataRepository.getTasks()).thenReturn(result);
        taskViewModel.getTasks().observeForever(taskAndProjects -> {
            assertEquals(taskAndProjects, tasks);
        });
    }



    @Test
    public void test_az_comparator() {
        MutableLiveData<List<TaskAndProject>> result = new MutableLiveData<>();
        final ArrayList<TaskAndProject> tasks = new ArrayList<>();
        tasks.add(taskAndProject1);
        tasks.add(taskAndProject3);
        tasks.add(taskAndProject2);
        result.setValue(tasks);
        Mockito.when(taskDataRepository.orderTaskByAsc()).thenReturn(result);
        taskViewModel.orderTaskByAsc().observeForever(taskAndProjects -> {
            assertEquals(Arrays.asList(taskAndProjects.toArray()), Arrays.asList(tasks.toArray()));
        });
    }

    @Test
    public void test_za_comparator() {
        MutableLiveData<List<TaskAndProject>> result = new MutableLiveData<>();
        final ArrayList<TaskAndProject> tasks = new ArrayList<>();
        tasks.add(taskAndProject2);
        tasks.add(taskAndProject3);
        tasks.add(taskAndProject1);
        result.setValue(tasks);
        Mockito.when(taskDataRepository.orderTaskByDesc()).thenReturn(result);
        taskViewModel.orderTaskByDesc().observeForever(taskAndProjects -> {
            assertSame(taskAndProjects, tasks);
        });
    }
    @Test
    public void test_recent_comparator() {
        MutableLiveData<List<TaskAndProject>> result = new MutableLiveData<>();
        final ArrayList<TaskAndProject> tasks = new ArrayList<>();
        tasks.add(taskAndProject3);
        tasks.add(taskAndProject2);
        tasks.add(taskAndProject1);
        result.setValue(tasks);
        Mockito.when(taskDataRepository.orderTaskByRecent()).thenReturn(result);
        taskViewModel.orderTaskByRecent().observeForever(taskAndProjects -> {
            assertSame(taskAndProjects.get(0).mTask.getCreationTimestamp(), task3.getCreationTimestamp());
            assertSame(taskAndProjects.get(1).mTask.getCreationTimestamp(), task2.getCreationTimestamp());
            assertSame(taskAndProjects.get(2).mTask.getCreationTimestamp(), task1.getCreationTimestamp());
        });
    }

    @Test
    public void test_old_comparator() {
        MutableLiveData<List<TaskAndProject>> result = new MutableLiveData<>();
        final ArrayList<TaskAndProject> tasks = new ArrayList<>();
        tasks.add(taskAndProject1);
        tasks.add(taskAndProject2);
        tasks.add(taskAndProject3);
        result.setValue(tasks);
        Mockito.when(taskDataRepository.orderTaskByOlder()).thenReturn(result);
        taskViewModel.orderTaskByOlder().observeForever(taskAndProjects -> {
            assertSame(taskAndProjects.get(0).mTask.getCreationTimestamp(), task1.getCreationTimestamp());
            assertSame(taskAndProjects.get(1).mTask.getCreationTimestamp(), task2.getCreationTimestamp());
            assertSame(taskAndProjects.get(2).mTask.getCreationTimestamp(), task3.getCreationTimestamp());
        });
    }
}