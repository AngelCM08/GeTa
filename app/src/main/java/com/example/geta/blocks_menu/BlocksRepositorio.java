package com.example.geta.blocks_menu;

import com.example.geta.R;

import java.util.ArrayList;
import java.util.List;

public class BlocksRepositorio {
    List<Block> blocks = new ArrayList<>();

    interface Callback {
        void cuandoFinalice(List<Block> blocks);
    }

    BlocksRepositorio(){
        blocks.add(new Block("Tareas de Casa", R.drawable.tareas_casa, "block_color1"));
        blocks.add(new Block("Proyecto Alfa", R.drawable.alfa, "block_color2"));
        blocks.add(new Block("Proyecto Beta", R.drawable.beta, "block_color3"));
        blocks.add(new Block("Proyecto Gamma", R.drawable.gamma, "block_color4"));
        blocks.add(new Block("Tareas de Casa", R.drawable.tareas_casa, "block_color1"));
        blocks.add(new Block("Proyecto Alfa", R.drawable.alfa, "block_color2"));
        blocks.add(new Block("Proyecto Beta", R.drawable.beta, "block_color3"));
        blocks.add(new Block("Proyecto Gamma", R.drawable.gamma, "block_color4"));
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
