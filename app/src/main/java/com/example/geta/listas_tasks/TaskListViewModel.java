package com.example.geta.listas_tasks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class TaskListViewModel extends AndroidViewModel {

    TaskListRepositorio taskListsRepositorio;

    MutableLiveData<List<TaskList>> listTaskListsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<TaskList> taskListSeleccionado = new MutableLiveData<>();

    public TaskListViewModel(@NonNull Application application) {
        super(application);

        taskListsRepositorio = new TaskListRepositorio();

        listTaskListsMutableLiveData.setValue(taskListsRepositorio.obtener());
    }


    MutableLiveData<List<TaskList>> obtener(){
        return listTaskListsMutableLiveData;
    }

    void insertar(TaskList taskList){
        taskListsRepositorio.insertar(taskList, new TaskListRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<TaskList> taskLists) {
                listTaskListsMutableLiveData.setValue(taskLists);
            }
        });
    }

    void eliminar(TaskList taskList){
        taskListsRepositorio.eliminar(taskList, new TaskListRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<TaskList> taskLists) {
                listTaskListsMutableLiveData.setValue(taskLists);
            }
        });
    }

    void actualizar(TaskList taskList, String nombre){
        taskListsRepositorio.actualizar(taskList, nombre, new TaskListRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<TaskList> taskLists) {
                listTaskListsMutableLiveData.setValue(taskLists);
            }
        });
    }

    void seleccionar(TaskList taskList){
        taskListSeleccionado.setValue(taskList);
    }

    MutableLiveData<TaskList> seleccionado(){
        return taskListSeleccionado;
    }
}
