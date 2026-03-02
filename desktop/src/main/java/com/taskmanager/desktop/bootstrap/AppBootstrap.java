package com.taskmanager.desktop.bootstrap;

import com.taskmanager.core.domain.Task;
import com.taskmanager.storage.sqlite.SqliteTaskRepository;

import java.util.List;

public class AppBootstrap {

    public static void main(String[] args) {

        var repo = new SqliteTaskRepository();

        // save one task
        repo.save(Task.create("First persisted task"));

        // fetch all tasks
        List<Task> tasks = repo.findAll();

        // print them
        tasks.forEach(t -> System.out.println(t.getTitle()));
    }
}
