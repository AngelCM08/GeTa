package com.example.geta.blocks_menu;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geta.R;
import com.example.geta.databinding.FragmentBlocksMenuBinding;
import com.example.geta.databinding.ViewholderBlockMenuBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BlocksMenuFragment extends Fragment {

    private FragmentBlocksMenuBinding binding;
    private BlocksViewModel blocksViewModel;
    private NavController navController;
    private int posicionQueEliminar;
    private boolean activated = false;

    public BlocksMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentBlocksMenuBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        blocksViewModel = new ViewModelProvider(requireActivity()).get(BlocksViewModel.class);
        navController = Navigation.findNavController(view);
        BlocksAdapter blocksAdapter = new BlocksAdapter();

        binding.userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_blocksMenuFragment_to_profileFragment);
            }
        });

        binding.addBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_blocksMenuFragment_to_newProjectFragment);
            }
        });

        binding.calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.compactCalendar.setVisibility(View.VISIBLE);
            }
        });

        binding.compactCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.compactCalendar.setVisibility(View.INVISIBLE);
            }
        });

        binding.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_blocksMenuFragment_to_loginFragment);
            }
        });

        binding.aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Block block = blocksAdapter.obtenerBlock(posicionQueEliminar);
                blocksViewModel.eliminar(block);
                binding.areUSure.setVisibility(View.INVISIBLE);
            }
        });
        binding.cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.areUSure.setVisibility(View.INVISIBLE);
                navController.navigate(R.id.action_blocksMenuFragment_self);
            }
        });

        binding.blocksMenuRecyclerView.setAdapter(blocksAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                posicionQueEliminar = viewHolder.getAdapterPosition();
                binding.areUSure.setVisibility(View.VISIBLE);
            }
        }).attachToRecyclerView(binding.blocksMenuRecyclerView);

        blocksViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Block>>() {
            @Override
            public void onChanged(List<Block> blocks) {
                blocksAdapter.establecerLista(blocks);
            }
        });

        setCalendarViewAppearance(view);
    }

    class BlocksAdapter extends RecyclerView.Adapter<BlockMenuViewHolder> {

        List<Block> blocks;

        @NonNull
        @Override
        public BlockMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BlockMenuViewHolder(ViewholderBlockMenuBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull BlockMenuViewHolder holder, int position) {
            Block block = blocks.get(position);

            holder.binding.title.setText(block.nombre);
            holder.binding.image.setImageResource(block.image);

            holder.binding.block.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt("color", block.color);
                blocksViewModel.seleccionar(block);
                navController.navigate(R.id.action_blocksMenuFragment_to_loadingFragment, bundle);
            });
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

    static class BlockMenuViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderBlockMenuBinding binding;

        public BlockMenuViewHolder(ViewholderBlockMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private void setCalendarViewAppearance(@NonNull View view) {
        SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", new Locale("es", "ES"));

        binding.compactcalendarView.setUseThreeLetterAbbreviation(true);
        binding.monthText.setText(dateFormatForMonth.format(binding.compactcalendarView.getFirstDayOfCurrentMonth()));
        binding.compactcalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = binding.compactcalendarView.getEvents(dateClicked);
                binding.compactcalendarView.addEvent(new Event(Color.rgb(72,121,156),dateClicked.getTime()));
                if (events.size() > 0) {
                    Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                binding.compactcalendarView.setCurrentDate(firstDayOfNewMonth);
                binding.monthText.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });
    }
}