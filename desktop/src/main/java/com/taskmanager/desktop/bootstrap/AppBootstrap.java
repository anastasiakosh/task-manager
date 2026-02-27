package com.taskmanager.desktop.bootstrap;

import com.taskmanager.core.domain.Task;
import com.taskmanager.storage.sqlite.SqliteTaskRepository;

public class AppBootstrap {
    public static void main(String[] args) {
        var repo = new SqliteTaskRepository();

        var task = Task.create("First persisted task");
        repo.save(task);

        var tasks = repo.findAll();

        task.forEach(t -> System.out.println(t.getTitle()));
    }
}
