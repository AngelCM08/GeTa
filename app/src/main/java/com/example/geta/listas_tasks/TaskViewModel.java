package com.example.geta.listas_tasks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    TaskRepositorio taskRepositorio;

    MutableLiveData<List<Task>> listTasksMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Task> taskSeleccionado = new MutableLiveData<>();

    public TaskViewModel(@NonNull Application application) {
        super(application);

        taskRepositorio = new TaskRepositorio();

        listTasksMutableLiveData.setValue(taskRepositorio.obtener());
    }


    MutableLiveData<List<Task>> obtener(){
        return listTasksMutableLiveData;
    }

    void insertar(Task task){
        taskRepositorio.insertar(task, new TaskRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Task> tasks) {
                listTasksMutableLiveData.setValue(tasks);
            }
        });
    }

    void eliminar(Task task){
        taskRepositorio.eliminar(task, new TaskRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Task> tasks) {
                listTasksMutableLiveData.setValue(tasks);
            }
        });
    }

    void actualizar(Task task, String nombre, String descripcion){
        taskRepositorio.actualizar(task, nombre, descripcion, new TaskRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Task> tasks) {
                listTasksMutableLiveData.setValue(tasks);
            }
        });
    }


    void seleccionar(Task task){
        taskSeleccionado.setValue(task);
    }

    MutableLiveData<Task> seleccionado(){
        return taskSeleccionado;
    }
}
