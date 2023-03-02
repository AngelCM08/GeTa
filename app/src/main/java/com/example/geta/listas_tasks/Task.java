package com.example.geta.listas_tasks;

import java.util.List;

public class Task {
    String nombre;
    String descripcion;
    public List<String> usuarios_asignados;

    public Task(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Task(String nombre, String descripcion, List<String> usuarios_asignados) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuarios_asignados = usuarios_asignados;
    }
}
