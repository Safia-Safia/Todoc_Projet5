package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;

import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Unit tests for tasks
 *
 * @author Gaëtan HERFRAY
 */
@RunWith(AndroidJUnit4.class)
public class TaskUnitTest {


    // FOR DATA
    private TodocDatabase database;

    // DATA SET FOR TEST

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final Task task1 = new Task(1, 1, "aaa", 123);
    private final Task task2 = new Task(2, 2, "zzz", 124);
    private final Task task3 = new Task(3, 3, "hhh", 125);


    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getTargetContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }


    @Test
    public void test_projects() {
        final Task task1 = new Task(1, 1, "task 1", new Date().getTime());
        final Task task2 = new Task(2, 2, "task 2", new Date().getTime());
        final Task task3 = new Task(3, 3, "task 3", new Date().getTime());
        final Task task4 = new Task(4, 4, "task 4", new Date().getTime());

        assertEquals("Projet Tartampion", task1.projectOwnerId);
        assertEquals("Projet Lucidia", task2.projectOwnerId);
        assertEquals("Projet Circus", task3.projectOwnerId);
        assertNull(task4.projectOwnerId);
    }

    @Test
    public void test_az_comparator() {

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        //Collections.sort(tasks, new Task.TaskAZComparator());

        assertSame(tasks.get(0), task1);
        assertSame(tasks.get(1), task3);
        assertSame(tasks.get(2), task2);
    }

    @Test
    public void test_za_comparator() {

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        //Collections.sort(tasks, new Task.TaskZAComparator());

        assertSame(tasks.get(0), task2);
        assertSame(tasks.get(1), task3);
        assertSame(tasks.get(2), task1);
    }

    @Test
    public void test_recent_comparator() {

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);


        //Collections.sort(tasks, new Task.TaskRecentComparator());

        assertSame(tasks.get(0), task3);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task1);
    }

    @Test
    public void test_old_comparator() {

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
       // Collections.sort(tasks, new Task.TaskOldComparator());

        assertSame(tasks.get(0), task1);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task3);
    }
}