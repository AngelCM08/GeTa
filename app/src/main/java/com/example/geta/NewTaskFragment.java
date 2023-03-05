package com.example.geta;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geta.databinding.FragmentBlockBinding;
import com.example.geta.databinding.FragmentNewTaskBinding;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class NewTaskFragment extends Fragment {

    private FragmentNewTaskBinding binding;
    private NavController navController;
    private int block_color;

    private static final String[] USERS_TO_ASIGN = {
            "Ángel Castro Merino",
            "Joel López Acosta",
            "Tarik Aabouch Azougarth",
            "Xiaojan Zhen",
            "Daniel Martínez Cruz"
    };

    public NewTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            block_color = getArguments().getInt("color");
        }

        return (binding = FragmentNewTaskBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        Bundle bundle = new Bundle();
        bundle.putInt("color", block_color);

        binding.addBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_newTaskFragment_to_taskDescriptionFragment, bundle);
            }
        });

        binding.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_newTaskFragment_to_blockFragment, bundle);
            }
        });

        MaterialSpinner spinner = view.findViewById(R.id.spinner);
        spinner.setItems(USERS_TO_ASIGN);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Usuario " + item + " asignado!", Snackbar.LENGTH_LONG).show();
            }
        });

    }
}