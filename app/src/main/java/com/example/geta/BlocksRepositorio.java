package com.example.geta;

import java.util.ArrayList;
import java.util.List;

public class BlocksRepositorio {
    List<Block> blocks = new ArrayList<>();

    interface Callback {
        void cuandoFinalice(List<Block> blocks);
    }

    BlocksRepositorio(){
        blocks.add(new Block("Tareas de Casa", R.drawable.tareas_casa));
        blocks.add(new Block("Proyecto Alfa", R.drawable.alfa));
        blocks.add(new Block("Proyecto Beta", R.drawable.beta));
        blocks.add(new Block("Proyecto Gamma", R.drawable.gamma));
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
