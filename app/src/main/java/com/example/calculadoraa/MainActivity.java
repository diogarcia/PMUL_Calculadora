package com.example.calculadoraa;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    //TODO THIS IS A TEST
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MaterialButton btnClear = findViewById(R.id.buttonClear);
        MaterialButton btnPlus = findViewById(R.id.buttonPlus);
        MaterialButton btnEqual = findViewById(R.id.buttonEqual);

        TextView pantalla = findViewById(R.id.textView);

        List<Integer> botonesNumeros = new ArrayList<>();
        botonesNumeros.add(R.id.button0);
        botonesNumeros.add(R.id.button1);
        botonesNumeros.add(R.id.button2);
        botonesNumeros.add(R.id.button3);
        botonesNumeros.add(R.id.button4);
        botonesNumeros.add(R.id.button5);
        botonesNumeros.add(R.id.button6);
        botonesNumeros.add(R.id.button7);
        botonesNumeros.add(R.id.button8);
        botonesNumeros.add(R.id.button9);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantalla.setText("");
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantalla.append("+");
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strScreen = pantalla.getText().toString();
                boolean strOK = isGoodString(strScreen);

                if(!strOK){
                    pantalla.setText("-1");
                    Toast.makeText(getApplicationContext(), "Secuencia erronea pulsa 'C'", Toast.LENGTH_SHORT).show();
                } else {

                    int result = calculate(strScreen);
                    pantalla.setText(String.valueOf(result));
                }
            }
        });

        View.OnClickListener listenerNumeros = new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                MaterialButton boton = (MaterialButton) view;
                String numero = boton.getText().toString();
                pantalla.append(numero);
            }
        };

        for(int id: botonesNumeros){
            findViewById(id).setOnClickListener(listenerNumeros);
        }

    }

    private int calculate(String cadena){

        if(!cadena.contains("+")){
            return Integer.parseInt(cadena);
        }else{
            String [] cortes = cadena.split("\\+",2);
            String cad1 = cortes[0];
            String cad2 = cortes[1];

            return calculate(cad1) + calculate(cad2);
        }
    }

    private boolean isGoodString(String cadena) {

        if (cadena.equals("")) return false;
        if (cadena.contains("++")) return false;

        for(int i = 0; i<cadena.length(); i++){
            if(cadena.charAt(i) != '+' && !Character.isDigit(cadena.charAt(i))){
                return false;
            }
        }

        return true;
    }
}