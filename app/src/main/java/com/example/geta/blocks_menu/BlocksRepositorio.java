package com.example.geta.blocks_menu;

import com.example.geta.R;
import com.example.geta.listas_tasks.TaskListRepositorio;

import java.util.ArrayList;
import java.util.List;

public class BlocksRepositorio {
    List<Block> blocks = new ArrayList<>();

    interface Callback {
        void cuandoFinalice(List<Block> blocks);
    }

    BlocksRepositorio(){
        blocks.add(new Block("Tareas de Casa", R.drawable.tareas_casa, R.color.block_color1, new TaskListRepositorio().obtener()));
        blocks.add(new Block("Proyecto Alfa", R.drawable.alfa, R.color.block_color2, new TaskListRepositorio().obtener()));
        blocks.add(new Block("Proyecto Beta", R.drawable.beta, R.color.block_color3, new TaskListRepositorio().obtener()));
        blocks.add(new Block("Proyecto Gamma", R.drawable.gamma, R.color.block_color4, new TaskListRepositorio().obtener()));
        blocks.add(new Block("Tareas de Casa", R.drawable.tareas_casa, R.color.block_color1, new TaskListRepositorio().obtener()));
        blocks.add(new Block("Proyecto Alfa", R.drawable.alfa, R.color.block_color2, new TaskListRepositorio().obtener()));
        blocks.add(new Block("Proyecto Beta", R.drawable.beta, R.color.block_color3, new TaskListRepositorio().obtener()));
        blocks.add(new Block("Proyecto Gamma", R.drawable.gamma, R.color.block_color4, new TaskListRepositorio().obtener()));
    }

    List<Block> obtener() {
        return blocks;
    }

    void insertar(Block block, Callback callback){
        blocks.add(block);
        callback.cuandoFinalice(blocks);
    }

    void eliminar(Block block, Callback callback) {
        blocks.remove(block);
        callback.cuandoFinalice(blocks);
    }

    void actualizar(Block block, String nombre, int image, Callback callback) {
        block.nombre = nombre;
        block.image = image;
        callback.cuandoFinalice(blocks);
    }
}
