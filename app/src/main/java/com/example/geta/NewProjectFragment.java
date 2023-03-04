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

import com.example.geta.databinding.FragmentNewProjectBinding;
import com.example.geta.databinding.FragmentProfileBinding;

public class NewProjectFragment extends Fragment {
    FragmentNewProjectBinding binding;
    NavController navController;

    public NewProjectFragment() {
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
        return (binding = FragmentNewProjectBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_newProjectFragment_to_newListFragment);
            }
        });

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_newProjectFragment_to_blocksMenuFragment);
            }
        });

        binding.blockImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.permitAccess.setVisibility(View.VISIBLE);
            }
        });

        binding.denegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.permitAccess.setVisibility(View.INVISIBLE);
            }
        });

        binding.permitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.permitAccess.setVisibility(View.INVISIBLE);
            }
        });

        binding.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.checkbox.setChecked(binding.checkbox.isChecked());
            }
        });

        binding.color1Shadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.color1Shadow.getVisibility() == View.VISIBLE){
                    binding.color1Shadow.setVisibility(View.INVISIBLE);
                    binding.color2Shadow.setVisibility(View.VISIBLE);
                    binding.color3Shadow.setVisibility(View.VISIBLE);
                    binding.color4Shadow.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.color2Shadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.color2Shadow.getVisibility() == View.VISIBLE){
                    binding.color1Shadow.setVisibility(View.VISIBLE);
                    binding.color2Shadow.setVisibility(View.INVISIBLE);
                    binding.color3Shadow.setVisibility(View.VISIBLE);
                    binding.color4Shadow.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.color3Shadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.color3Shadow.getVisibility() == View.VISIBLE){
                    binding.color1Shadow.setVisibility(View.VISIBLE);
                    binding.color2Shadow.setVisibility(View.VISIBLE);
                    binding.color3Shadow.setVisibility(View.INVISIBLE);
                    binding.color4Shadow.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.color4Shadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.color4Shadow.getVisibility() == View.VISIBLE){
                    binding.color1Shadow.setVisibility(View.VISIBLE);
                    binding.color2Shadow.setVisibility(View.VISIBLE);
                    binding.color3Shadow.setVisibility(View.VISIBLE);
                    binding.color4Shadow.setVisibility(View.INVISIBLE);
                }
            }
        });


    }
}