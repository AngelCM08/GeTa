package com.example.geta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geta.databinding.FragmentRecyclerBlocksBinding;
import com.example.geta.databinding.ViewholderBlockBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerBlocksFragment extends Fragment {

    private FragmentRecyclerBlocksBinding binding;
    private BlocksViewModel blocksViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerBlocksBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        blocksViewModel = new ViewModelProvider(requireActivity()).get(BlocksViewModel.class);
        navController = Navigation.findNavController(view);

        // navegar a NuevoElemento cuando se hace click en el FloatingActionButton
        /*binding.irANuevoBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_recyclerBlocksFragment_to_nuevoBlockFragment);
            }
        });*/

        // crear el Adaptador
        BlocksAdapter blocksAdapter = new BlocksAdapter(blocksViewModel.blocksRepo.obtener());

        // asociar el Adaptador con el RecyclerView
        binding.recyclerView.setAdapter(blocksAdapter);

        // obtener el array de Elementos, y pasarselo al Adaptador
        blocksViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Block>>() {
            @Override
            public void onChanged(List<Block> blocks) {
                blocksAdapter.establecerLista(blocks);
            }
        });
    }

    class BlocksAdapter extends RecyclerView.Adapter<BlockViewHolder> {

        // referencia al Array que obtenemos del ViewModel
        List<Block> blocks = new ArrayList<>();

        // Constructor
        public BlocksAdapter(List<Block> blocks){
            this.blocks = blocks;
        }

        // crear un nuevo ViewHolder
        @NonNull
        @Override
        public BlockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BlockViewHolder(ViewholderBlockBinding.inflate(getLayoutInflater(), parent, false));
        }

        // rellenar un ViewHolder en una posición del Recycler con los datos del block que
        // esté en esa misma posición en el Array
        @Override
        public void onBindViewHolder(@NonNull BlockViewHolder holder, int position) {

            Block block = blocks.get(position);

            holder.binding.title.setText(block.title);
            holder.binding.imatge.setImageResource(block.imatge);

            /*holder.binding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navController.navigate(R.id.);
                }
            });*/
        }

        // informar al Recycler de cuántos blocks habrá en la lista
        @Override
        public int getItemCount() {
            return blocks != null ? blocks.size() : 0;
        }

        // establecer la referencia a la lista, y notificar al Recycler para que se regenere
        public void establecerLista(List<Block> blocks){
            this.blocks = blocks;
            notifyDataSetChanged();
        }
    }

    // Clase para inicializar el ViewBinding en los ViewHolder
    static class BlockViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderBlockBinding binding;

        public BlockViewHolder(ViewholderBlockBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}