package com.example.geta.listas_tasks;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.geta.R;
import com.example.geta.databinding.FragmentBlockBinding;
import com.example.geta.databinding.ViewholderTaskMenuBinding;
import com.example.geta.databinding.ViewholderTasklistBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BlockFragment extends Fragment {
    private FragmentBlockBinding binding;
    private NavController navController;
    private int block_color;
    private boolean recharged;

    public BlockFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBlockBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            block_color = getArguments().getInt("color");
            recharged = getArguments().getBoolean("recharged");
        }

        /*if(recharged){
            binding.scrollView.getChildAt(0).post(new Runnable() {
                @Override
                public void run() {
                    binding.scrollView.smoothScrollTo(binding.scrollView.getChildAt(0).getWidth(), 0);
                }
            });
        }*/

        return (binding).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TaskListViewModel taskListViewModel = new ViewModelProvider(requireActivity()).get(TaskListViewModel.class);
        navController = Navigation.findNavController(view);
        TaskListAdapter taskListAdapter = new TaskListAdapter();

        binding.addBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Task> taskList = null;
                taskListAdapter.taskListList.add(new TaskList("Lista vacía", taskList));
                Bundle bundle = new Bundle();
                bundle.putInt("color", block_color);
                bundle.putBoolean("recharged", true);
                navController.navigate(R.id.action_blockFragment_self, bundle);
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
                navController.navigate(R.id.action_blockFragment_to_blocksMenuFragment);
            }
        });

        binding.taskListRecyclerView.setAdapter(taskListAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {}

        }).attachToRecyclerView(binding.taskListRecyclerView);

        taskListViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<TaskList>>() {
            @Override
            public void onChanged(List<TaskList> taskListList) {
                taskListAdapter.establecerLista(taskListList);
            }
        });

        setCalendarViewAppearance(view);
    }

    public class TaskListAdapter extends RecyclerView.Adapter<ViewholderTasklist> {
        List<TaskList> taskListList;

        @NonNull
        @Override
        public ViewholderTasklist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewholderTasklist(ViewholderTasklistBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewholderTasklist holder, int position) {
            TaskList taskList = taskListList.get(position);
            TaskViewModel taskViewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
            holder.binding.listName.setText(taskList.nombre);
            holder.binding.addNewTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("color", block_color);
                    navController.navigate(R.id.action_blockFragment_to_newTaskFragment, bundle);
                }
            });

            // Crea y establece el adaptador para el RecyclerView hijo
            TaskAdapter taskAdapter = new TaskAdapter();
            holder.binding.TasksRecyclerView.setAdapter(taskAdapter);

            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                    ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                    ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return true;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {}

            }).attachToRecyclerView(holder.binding.TasksRecyclerView);

            taskViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
                @Override
                public void onChanged(List<Task> taskList) {
                    taskAdapter.establecerLista(taskList);
                }
            });
        }

        @Override
        public int getItemCount() {
            return taskListList != null ? taskListList.size() : 0;
        }

        public void establecerLista(List<TaskList> taskListList){
            this.taskListList = taskListList;
            notifyDataSetChanged();
        }

        public TaskList obtenerBlock(int posicion){
            return taskListList.get(posicion);
        }
    }

    static class ViewholderTasklist extends RecyclerView.ViewHolder {
        private final ViewholderTasklistBinding binding;

        public ViewholderTasklist(ViewholderTasklistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class TaskAdapter extends RecyclerView.Adapter<ViewholderTask> {
        List<Task> taskList;

        @NonNull
        @Override
        public ViewholderTask onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewholderTask(ViewholderTaskMenuBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewholderTask holder, int position) {
            Task task = this.taskList.get(position);

            holder.binding.title.setText(task.nombre);
            holder.binding.element.setBackgroundColor(getResources().getColor(block_color));
            holder.binding.cross.setBackgroundColor(getResources().getColor(block_color));

            holder.binding.element.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt("color", block_color);
                navController.navigate(R.id.action_blockFragment_to_taskDescriptionFragment, bundle);
            });

            holder.binding.cross.setOnClickListener(v -> {
                taskList.remove(position);
                notifyDataSetChanged();
            });
        }

        @Override
        public int getItemCount() {
            return taskList != null ? taskList.size() : 0;
        }

        public void establecerLista(List<Task> taskList){
            this.taskList = taskList;
            notifyDataSetChanged();
        }

        public Task obtenerTask(int posicion){
            return taskList.get(posicion);
        }
    }

    static class ViewholderTask extends RecyclerView.ViewHolder {
        private final ViewholderTaskMenuBinding binding;

        public ViewholderTask(ViewholderTaskMenuBinding binding) {
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
