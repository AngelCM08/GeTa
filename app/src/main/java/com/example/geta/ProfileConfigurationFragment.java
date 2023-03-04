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

import com.example.geta.databinding.FragmentProfileBinding;
import com.example.geta.databinding.FragmentProfileConfigurationBinding;

public class ProfileConfigurationFragment extends Fragment {
    NavController navController;
    FragmentProfileConfigurationBinding binding;

    public ProfileConfigurationFragment() {
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
        return (binding = FragmentProfileConfigurationBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_profileConfigurationFragment_to_blocksMenuFragment);
            }
        });

        binding.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_profileConfigurationFragment_to_profileFragment);
            }
        });

        binding.userImage.setOnClickListener(new View.OnClickListener() {
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
                binding.userImage.setImageResource(R.drawable.user);
                binding.userImage.setPadding(0,0,0,0);
            }
        });

        binding.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.checkbox.setChecked(binding.checkbox.isChecked());
            }
        });
    }

}