package com.example.geta;

import java.util.ArrayList;
import java.util.List;

public class BlocksRepo {
    List<Block> blockList = new ArrayList<>();

    interface Callback {
        void cuandoFinalice(List<Block> blockList);
    }

    BlocksRepo(){
        blockList.add(new Block("Tareas de Casa", R.drawable.tareas_casa));
        blockList.add(new Block("Proyecto Alfa", R.drawable.alfa));
        blockList.add(new Block("Proyecto Beta", R.drawable.beta));
        blockList.add(new Block("Proyecto Gamma", R.drawable.gamma));
    }

    List<Block> obtener() {
        return blockList;
    }

    void insertar(Block block, Callback callback){
        blockList.add(block);
        callback.cuandoFinalice(blockList);
    }

    void eliminar(Block block, Callback callback) {
        blockList.remove(block);
        callback.cuandoFinalice(blockList);
    }

    void actualizar(Block block, String title, int imatge, Callback callback) {
        block.title = title;
        block.imatge = imatge;
        callback.cuandoFinalice(blockList);
    }
}
