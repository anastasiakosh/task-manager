package com.taskmanager.core.domain;

import java.time.Instant;
import java.util.UUID;
import com.taskmanager.core.domain.TaskStatus;
import com.taskmanager.core.domain.SyncState;

public class Task {

   private final UUID id;
   private String title;
   private TaskStatus status;
   private Instant updatedAt;
   private SyncState syncState;

   public Task(UUID id, String title) {
       this.id = id;
       this.title = title;
       this.status = TaskStatus.OPEN;
       this.updatedAt = Instant.now();
       this.syncState = SyncState.LOCAL_DIRTY;
   }

   public static Task create(String title) {
       return new Task(UUID.randomUUID(), title);
   }

   public void complete() {
       this.status = TaskStatus.COMPLETED;
       touch();
   }

   public void rename(String title) {
       this.title = title;
       touch();
   }

   private void touch() {
       updatedAt = Instant.now();
       syncState = SyncState.LOCAL_DIRTY;
   }

   public UUID getId() { return id; }
   public String getTitle() { return title; }
   public TaskStatus getStatus() { return status; }
}
