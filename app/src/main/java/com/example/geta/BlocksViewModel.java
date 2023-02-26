package com.example.geta;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class BlocksViewModel extends AndroidViewModel {
    BlocksRepo blocksRepo;
    MutableLiveData<List<Block>> listElementosMutableLiveData = new MutableLiveData<>();

    public BlocksViewModel(@NonNull Application application) {
        super(application);

        blocksRepo = new BlocksRepo();
        listElementosMutableLiveData.setValue(blocksRepo.obtener());
    }

    MutableLiveData<List<Block>> obtener(){
        return listElementosMutableLiveData;
    }

    void insertar(Block block){
        blocksRepo.insertar(block, new BlocksRepo.Callback() {
            @Override
            public void cuandoFinalice(List<Block> blocks) {
                listElementosMutableLiveData.setValue(blocks);
            }
        });
    }

    void eliminar(Block block){
        blocksRepo.eliminar(block, new BlocksRepo.Callback() {
            @Override
            public void cuandoFinalice(List<Block> blocks) {
                listElementosMutableLiveData.setValue(blocks);
            }
        });
    }

    void actualizar(Block block, String title, int imatge){
        blocksRepo.actualizar(block, title, imatge, new BlocksRepo.Callback() {
            @Override
            public void cuandoFinalice(List<Block> blocks) {
                listElementosMutableLiveData.setValue(blocks);
            }
        });
    }
}