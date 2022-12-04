package com.example.sudoku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private Spinner[][] matrix = new Spinner[9][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout sudokuLayout = findViewById(R.id.sudokuLayout);
        SudokuModel.creaPartida();
        for(int row = 0; row < 9; row++) {
            TableRow tableRow = new TableRow(this);
            for (int col = 0; col < 9; col++) {
                CharSequence[] numeros = {"0","1","2","3","4","5","6","7","8","9"};
                Spinner spinner = new Spinner(this);
                spinner.setBackground(getDrawable(R.drawable.border));
                spinner.setTag(R.id.fila, row);
                spinner.setTag(R.id.col, col);
                spinner.setPadding(5, 5, 5, 5);
                spinner.setTag("init");
                if (SudokuModel.getVal(row, col) != 0) {
                    spinner.setSelection(SudokuModel.getVal(row, col));
                    spinner.setEnabled(false);
                }
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int b, long l) {
                        if (adapterView.getTag().equals("init")) {
                            adapterView.setTag("initn't");
                        } else {
                            if (!(adapterView.getSelectedItem().toString().equals("0"))) {
                                int fila = (int) adapterView.getTag(R.id.fila);
                                int columna = (int) adapterView.getTag(R.id.col);
                                int valor = Integer.parseInt(adapterView.getSelectedItem().toString());

                                if (SudokuModel.setVal(fila, columna, valor) == -1) {
                                    Toast.makeText(getApplicationContext(), "T'has equivocat!!!", Toast.LENGTH_LONG).show();
                                } else {
                                    if (SudokuModel.checkCompleted()) {
                                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                        alertDialog.setTitle("Sudoku");
                                        alertDialog.setMessage("Enhorabona, has completat el sudoku!");

                                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int l) {
                                            }
                                        });
                                        alertDialog.show();
                                    }
                                }
                            }
                            refrescaGUI();
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, numeros);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                matrix[row][col]=spinner;
                tableRow.addView(spinner);
            }
            sudokuLayout.addView(tableRow);
        }
        refrescaGUI();
    }
    private void refrescaGUI(){
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                matrix[i][j].setSelection(SudokuModel.getVal(i,j));
            }
        }
    }
}