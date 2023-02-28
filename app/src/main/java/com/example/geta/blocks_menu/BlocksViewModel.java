package com.example.geta.blocks_menu;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class BlocksViewModel extends AndroidViewModel {

    BlocksRepositorio blocksRepositorio;

    MutableLiveData<List<Block>> listBlocksMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Block> blockSeleccionado = new MutableLiveData<>();

    public BlocksViewModel(@NonNull Application application) {
        super(application);

        blocksRepositorio = new BlocksRepositorio();

        listBlocksMutableLiveData.setValue(blocksRepositorio.obtener());
    }


    MutableLiveData<List<Block>> obtener(){
        return listBlocksMutableLiveData;
    }

    void insertar(Block block){
        blocksRepositorio.insertar(block, new BlocksRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Block> blocks) {
                listBlocksMutableLiveData.setValue(blocks);
            }
        });
    }

    void eliminar(Block block){
        blocksRepositorio.eliminar(block, new BlocksRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Block> blocks) {
                listBlocksMutableLiveData.setValue(blocks);
            }
        });
    }

    void actualizar(Block block, String nombre, int image){
        blocksRepositorio.actualizar(block, nombre, image, new BlocksRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Block> blocks) {
                listBlocksMutableLiveData.setValue(blocks);
            }
        });
    }


    void seleccionar(Block block){
        blockSeleccionado.setValue(block);
    }

    MutableLiveData<Block> seleccionado(){
        return blockSeleccionado;
    }
}