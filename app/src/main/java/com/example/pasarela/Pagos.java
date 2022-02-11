package com.example.pasarela;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pasarela.Llamada.Sales;
import com.example.pasarela.Model.Regions;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Pagos extends AppCompatActivity {
    TextInputEditText phoneNumber, amount, amountWithTax, amountWithoutTax, tax;
    Button generaPago;
    String AccessToken = "l2nRlnbGSAyTg314Q8zbA5KWdzbN0LINaETgbFOxo0EDb4slS3yfz-0AiiFkDxVelP3ZgInPvU7QHblLr2snfH9mwZN-ZRDnJK-rb4ky-Fv-3I_c9wUrtbNu5eGoXOtCafyMOme-ybB8MS8b7q75anKL_dk95x6nL0bC4mfUc3kaTEpS7yiZJFHa_KLRtP1ml10wBxOc3Fh6qS-E6OcX_DwZhaJ31Usyi-2S_KakdzxbDOEnOW2Xuh6UgNpUaSjkntBwfgexjg3eWN9QnGlcwvYF5o_q5ThN71WydLgTDKUwvGUsCmAZ08yJwxAO8mekvvBsTa5Ul4FudMRaBAHuQe0Ltg0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);
        ArrayList<Regions> regions = new ArrayList<Regions>();
        phoneNumber = findViewById(R.id.txtphoneNumber);
        generaPago = findViewById(R.id.btnpagar);
        amount= findViewById(R.id.txtamount);
        tax= findViewById(R.id.txtTax);
        Sales sales = new Sales(this,AccessToken);
        generaPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double monto=  Double.parseDouble(String.valueOf(amount.getText()));
                Double iva= monto*0.12;
                Double montoSinIva = monto-iva;

                String json="{\n" +
                        "  \"phoneNumber\":\""+ phoneNumber.getText() +"\",\n" +
                        "  \"countryCode\": \"593\",\n" +
                        "  \"clientUserId\": \""+ phoneNumber.getText() +"\",\n" +
                        "  \"reference\": \"none\",\n" +
                        "  \"responseUrl\": \"http://paystoreCZ.com/confirm.php\",\n" +
                        "  \"amount\": "+ amount.getText() +",\n" +
                        "  \"amountWithTax\": "+amountWithTax.getText()+",\n" +
                        "  \"amountWithoutTax\": 0,\n" +
                        "  \"tax\": "+tax+",\n" +
                        "  \"clientTransactionId\": \"12345\"\n" +
                        "}";
                String res=sales.salesPost(json);
                Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
            }
        });

    }
}