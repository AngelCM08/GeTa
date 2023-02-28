package com.example.geta.listas_tasks;

import java.util.List;

public class TaskList {
    String nombre;
    List<Task> taskList;

    public TaskList(String nombre, List<Task> taskList) {
        this.nombre = nombre;
        this.taskList = taskList;
    }
}
