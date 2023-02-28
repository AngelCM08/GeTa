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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geta.databinding.FragmentBlocksMenuBinding;
import com.example.geta.databinding.ViewholderBlockBinding;
import com.example.geta.databinding.ViewholderBlockMenuBinding;

import java.util.List;

public class RecyclerBlocksMenuFragment extends Fragment {

    private FragmentBlocksMenuBinding binding;
    private BlocksViewModel blocksViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentBlocksMenuBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        blocksViewModel = new ViewModelProvider(requireActivity()).get(BlocksViewModel.class);
        navController = Navigation.findNavController(view);

        /*binding.irANuevoBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_recyclerBlocksFragment_to_nuevoBlockFragment);
            }
        });*/
        BlocksAdapter blocksAdapter = new BlocksAdapter();

        binding.blocksMenuRecyclerView.setAdapter(blocksAdapter);

        binding.blocksMenuRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Block block = blocksAdapter.obtenerBlock(posicion);
                blocksViewModel.eliminar(block);

            }
        }).attachToRecyclerView(binding.blocksMenuRecyclerView);

        blocksViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Block>>() {
            @Override
            public void onChanged(List<Block> blocks) {
                blocksAdapter.establecerLista(blocks);
            }
        });
    }

    class BlocksAdapter extends RecyclerView.Adapter<BlockViewHolder> {

        List<Block> blocks;

        @NonNull
        @Override
        public BlockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BlockViewHolder(ViewholderBlockMenuBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull BlockViewHolder holder, int position) {
            Block block = blocks.get(position);

            holder.binding.title.setText(block.nombre);
            holder.binding.image.setImageResource(block.image);

            /*holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    blocksViewModel.seleccionar(block);
                    navController.navigate(R.id.action_recyclerBlocksFragment_to_mostrarBlockFragment);
                }
            });*/
        }

        @Override
        public int getItemCount() {
            return blocks != null ? blocks.size() : 0;
        }

        public void establecerLista(List<Block> blocks){
            this.blocks = blocks;
            notifyDataSetChanged();
        }

        public Block obtenerBlock(int posicion){
            return blocks.get(posicion);
        }
    }

    static class BlockViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderBlockMenuBinding binding;

        public BlockViewHolder(ViewholderBlockMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
