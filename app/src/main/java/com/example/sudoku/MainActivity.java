package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout sudokuLayout = findViewById(R.id.sudokuLayout);
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        for (int row = 0; row < 9; row++) {
            TableRow fila = new TableRow(this);
            fila.setLayoutParams(params);
            for (int col = 0; col < 9; col++) {
                Spinner spinner = new Spinner(this);
                CharSequence[] nombres = {"0","1","2","3","4","5","6","7","8","9"};
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,
                        android.R.layout.simple_spinner_item, nombres);
                spinner.setAdapter(adapter);
                spinner.setBackground(null);
                spinner.setPadding(5, 5, 5, 5);
                spinner.setLayoutParams(params);
                fila.addView(spinner);
            }
            sudokuLayout.addView(fila);
        }
    }
}