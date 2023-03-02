package com.example.geta.listas_tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskRepositorio {
    List<Task> tasks = new ArrayList<>();

    interface Callback {
        void cuandoFinalice(List<Task> tasks);
    }

    public TaskRepositorio(){
        tasks.add(new Task("Tarea 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam et massa vel neque interdum ultrices.Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae;"));
        tasks.add(new Task("Tarea 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam et massa vel neque interdum ultrices.Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae;"));
        tasks.add(new Task("Tarea 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam et massa vel neque interdum ultrices.Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae;"));
        tasks.add(new Task("Tarea 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam et massa vel neque interdum ultrices.Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae;"));
    }

    public List<Task> obtener() {
        return tasks;
    }

    void insertar(Task task, TaskRepositorio.Callback callback){
        tasks.add(task);
        callback.cuandoFinalice(tasks);
    }

    void eliminar(Task task, TaskRepositorio.Callback callback) {
        tasks.remove(task);
        callback.cuandoFinalice(tasks);
    }

    void actualizar(Task task, String nombre, String descripcion, TaskRepositorio.Callback callback) {
        task.nombre = nombre;
        task.descripcion = descripcion;
        callback.cuandoFinalice(tasks);
    }
}
