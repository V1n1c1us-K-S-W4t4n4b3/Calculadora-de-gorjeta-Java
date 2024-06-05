package com.kzdev.calculadoradegorjeta;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editValor;
    private TextView textPercentage;
    private TextView textTip;
    private TextView textTotal;
    private double percentage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setupViews();
    }

    private void setupViews(){
        editValor = findViewById(R.id.tiet_valor);
        textPercentage = findViewById(R.id.text_porcentagem);
        textTip = findViewById(R.id.text_valor_gorjeta);
        textTotal = findViewById(R.id.text_valor_total);
        SeekBar seekBarTip = findViewById(R.id.seek_bar);

        seekBarTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percentage = progress;
                textPercentage.setText(Math.round(percentage) + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                calculate();
            }
        });
    }
    public void calculate() {

        String recoveryValor = editValor.getText().toString();

        if (recoveryValor.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Digite um valor primeiro!", Toast.LENGTH_LONG).show();

            textTip.setText("R$" + "0.00");

            textTotal.setText("R$" + "0.00");
        } else {

            double digitValor = Double.parseDouble(recoveryValor);

            double tip = digitValor * (percentage / 100);

            double total = tip + digitValor;

            textTip.setText("R$" + Math.round(tip));

            textTotal.setText("R$" + Math.round(total));
        }
    }
}