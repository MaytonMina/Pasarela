package com.example.pasarela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnpago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnpago = findViewById(R.id.btnPago);
        btnpago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intetdetalle = new Intent(v.getContext(),Pagos.class);
                startActivity(intetdetalle);
            }
        });
    }
}