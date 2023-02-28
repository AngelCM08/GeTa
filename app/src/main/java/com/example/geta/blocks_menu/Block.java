package com.example.geta.blocks_menu;

import com.example.geta.listas_tasks.TaskList;

import java.util.List;

public class Block {
    String nombre;
    int image;
    String color;
    List<TaskList> taskListList;

    public Block(String nombre, int image, String color) {
        this.nombre = nombre;
        this.image = image;
        this.color = color;
    }
}
