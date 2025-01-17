package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        findViewById(R.id.add_button).setOnClickListener(v -> addCity());
        findViewById(R.id.delete_button).setOnClickListener(v -> deleteCity());

        // Prepopulate the list
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin"};
        dataList = new ArrayList<>(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, dataList);
        cityList.setAdapter(cityAdapter);

        // Allow single item selection in the ListView
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    // Add City
    private void addCity() {
        String cityName = cityInput.getText().toString().trim();
        dataList.add(cityName);
        cityAdapter.notifyDataSetChanged();
        cityInput.setText(""); // Clear
    }

    // Delete City
    private void deleteCity() {
        int selectedPosition = cityList.getCheckedItemPosition();
        String selectedCity = dataList.get(selectedPosition);
        dataList.remove(selectedCity);
        cityAdapter.notifyDataSetChanged();
        cityList.clearChoices(); // Clear selection
    }
}
