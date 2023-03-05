package com.example.geta;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geta.databinding.FragmentLoadingBinding;

public class LoadingFragment extends Fragment {
    FragmentLoadingBinding binding;
    NavController navController;
    private int block_color;
    private boolean stop = false;

    public LoadingFragment() {
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
        binding = FragmentLoadingBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            block_color = getArguments().getInt("color");
        }

        return (binding).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        binding.emptyView.loading().show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putInt("color", block_color);
                bundle.putBoolean("recharged", true);
                if(!stop) navController.navigate(R.id.action_loadingFragment_to_blockFragment, bundle);
            }
        }, 2000);
    }
}