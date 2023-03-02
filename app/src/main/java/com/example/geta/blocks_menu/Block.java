package com.example.geta.blocks_menu;

import com.example.geta.listas_tasks.TaskList;

import java.util.List;

public class Block {
    String nombre;
    int image;
    int color;
    public List<TaskList> taskListList;

    public Block(String nombre, int image, int color, List<TaskList> taskListList) {
        this.nombre = nombre;
        this.image = image;
        this.color = color;
        this.taskListList = taskListList;
    }
}
