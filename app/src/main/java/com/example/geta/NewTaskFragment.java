package com.example.geta;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geta.databinding.FragmentBlockBinding;
import com.example.geta.databinding.FragmentNewTaskBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        setCalendarViewAppearance(view);
    }


    private void setCalendarViewAppearance(@NonNull View view) {
        SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", new Locale("es", "ES"));

        binding.compactcalendarView.setUseThreeLetterAbbreviation(true);
        binding.monthText.setText(dateFormatForMonth.format(binding.compactcalendarView.getFirstDayOfCurrentMonth()));
        binding.compactcalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = binding.compactcalendarView.getEvents(dateClicked);
                binding.compactcalendarView.addEvent(new Event(Color.BLUE,dateClicked.getTime()));
//                binding.compactcalendarView.addEvent();
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