package com.example.geta.listas_tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskListRepositorio {
    List<TaskList> taskListList = new ArrayList<>();

    interface Callback {
        void cuandoFinalice(List<TaskList> taskLists);
    }

    public TaskListRepositorio(){
        taskListList.add(new TaskList("Lista 1", new TaskRepositorio().obtener()));
        taskListList.add(new TaskList("Lista 2", new TaskRepositorio().obtener()));
        taskListList.add(new TaskList("Lista 3", new TaskRepositorio().obtener()));
        taskListList.add(new TaskList("Lista 4", new TaskRepositorio().obtener()));
    }

    public List<TaskList> obtener() {
        return taskListList;
    }

    void insertar(TaskList taskList, TaskListRepositorio.Callback callback){
        taskListList.add(taskList);
        callback.cuandoFinalice(taskListList);
    }

    void eliminar(TaskList taskList, TaskListRepositorio.Callback callback) {
        taskListList.remove(taskList);
        callback.cuandoFinalice(taskListList);
    }

    void actualizar(TaskList taskList, String nombre, TaskListRepositorio.Callback callback) {
        taskList.nombre = nombre;
        callback.cuandoFinalice(taskListList);
    }
}
