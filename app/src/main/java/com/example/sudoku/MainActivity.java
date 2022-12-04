package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner[][] spinners = new Spinner[9][9];

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
                CharSequence[] nombres = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,
                        android.R.layout.simple_spinner_item, nombres);
                spinner.setAdapter(adapter);
                spinner.setBackground(null);
                spinner.setTag(R.id.fila, row);
                spinner.setTag(R.id.col, col);
                spinner.setTag("init");
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (spinner.getTag().equals("init")) {
                            spinner.setTag("initn't");
                        } else {
                            SudokuModel.setVal((int) spinner.getTag(R.id.fila), (int) spinner.getTag(R.id.col), (int) spinner.getSelectedItem());
                            refrescaGUI();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
                spinner.setPadding(5, 5, 5, 5);
                spinner.setLayoutParams(params);
                spinners[row][col] = spinner;
                fila.addView(spinner);
                if (SudokuModel.getVal(row, col) != 0) {
                    spinner.setSelection(SudokuModel.getVal(row, col));
                    spinner.setEnabled(false);
                }
            }
            sudokuLayout.addView(fila);

        }
        Toast.makeText(this, "iniciando", Toast.LENGTH_SHORT);
        SudokuModel.creaPartida();
    }

    public void refrescaGUI() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                spinners[row][col].setSelection(SudokuModel.getVal(row, col));
                System.out.println("Spinner canviat");
            }
        }
    }
}
