package com.taskmanager.storage.sqlite;

import com.taskmanager.core.domain.Task;
import com.taskmanager.core.domain.TaskStatus;
import com.taskmanager.core.repository.TaskRepository;

import java.sql.*;
import java.time.Instant;
import java.util.*;

public class SqliteTaskRepository implements TaskRepository {

    @Override
    public void save(Task task) {
        try (Connection c = Database.connect()) {

            PreparedStatement ps = c.prepareStatement(
                    "INSERT OR REPLACE INTO tasks(id,title,status,updated_at,sync_state) VALUES(?,?,?,?,?)"
            );

            ps.setString(1, task.getId().toString());
            ps.setString(2, task.getTitle());
            ps.setString(3, task.getStatus().name());
            ps.setLong(4, Instant.now().toEpochMilli());
            ps.setString(5, "LOCAL_DIRTY");

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> findAll() {
        try (Connection c = Database.connect()) {

            ResultSet rs = c.createStatement().executeQuery("SELECT * FROM tasks");

            List<Task> list = new ArrayList<>();

            while (rs.next()) {
                list.add(map(rs));
            }

            return list;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Task> findById(UUID id) {
        try (Connection c = Database.connect()) {

            PreparedStatement ps = c.prepareStatement("SELECT * FROM tasks WHERE id=?");
            ps.setString(1, id.toString());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) return Optional.of(map(rs));
            return Optional.empty();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        try (Connection c = Database.connect()) {

            PreparedStatement ps = c.prepareStatement("DELETE FROM tasks WHERE id=?");
            ps.setString(1, id.toString());
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Task map(ResultSet rs) throws Exception {
        UUID id = UUID.fromString(rs.getString("id"));
        String title = rs.getString("title");

        Task task = new Task(id, title);

        if (TaskStatus.valueOf(rs.getString("status")) == TaskStatus.COMPLETED) {
            task.complete();
        }

        return task;
    }
}
