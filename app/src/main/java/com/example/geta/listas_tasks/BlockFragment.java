package com.example.geta.listas_tasks;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geta.blocks_menu.Block;
import com.example.geta.databinding.FragmentBlockBinding;
import com.example.geta.databinding.ViewholderTaskMenuBinding;
import com.example.geta.databinding.ViewholderTasklistBinding;

import java.util.List;

public class BlockFragment extends Fragment {
    private FragmentBlockBinding binding;
    private int block_color;
    private NavController navController;

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
        return (binding = FragmentBlockBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TaskListViewModel taskListViewModel = new ViewModelProvider(requireActivity()).get(TaskListViewModel.class);
        navController = Navigation.findNavController(view);

        /*binding.addBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_blocksMenuFragment_to_newProjectFragment);
            }
        });*/
        TaskListAdapter taskListAdapter = new TaskListAdapter();

        binding.taskListRecyclerView.setAdapter(taskListAdapter);

        taskListViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<TaskList>>() {
            @Override
            public void onChanged(List<TaskList> taskListList) {
                taskListAdapter.establecerLista(taskListList);
            }
        });
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

            // Crea y establece el adaptador para el RecyclerView hijo
            TaskAdapter taskAdapter = new TaskAdapter();
            holder.binding.TasksRecyclerView.setAdapter(taskAdapter);

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
            //holder.binding.element.setBackgroundColor();
        }

        @Override
        public int getItemCount() {
            return taskList != null ? taskList.size() : 0;
        }

        public void establecerLista(List<Task> taskList){
            this.taskList = taskList;
            notifyDataSetChanged();
        }

        public Task obtenerBlock(int posicion){
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
}
