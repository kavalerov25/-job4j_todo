package ru.job4j.todo.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"created", "description"})
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    private Timestamp created = Timestamp.valueOf(LocalDateTime.now());

    private boolean done;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Task(String description) {
        this.description = description;
    }


    public Task(String name, String description, Timestamp created, boolean done) {
        this.name = name;
        this.description = description;
        this.created = created;
        this.done = done;
    }
}