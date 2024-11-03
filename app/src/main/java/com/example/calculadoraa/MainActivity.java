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

/**
 * @author dioga
 * @version 1.0
 * @since 2024
 *
 *
 * **/

public class MainActivity extends AppCompatActivity {

    private Calculadora calculadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        calculadora = new Calculadora();

        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Declarción componente
        MaterialButton btnClear = findViewById(R.id.buttonClear);
        MaterialButton btnPlus = findViewById(R.id.buttonPlus);
        MaterialButton btnEqual = findViewById(R.id.buttonEqual);
        MaterialButton btnMult = findViewById(R.id.buttonMult);
        MaterialButton btnMinus = findViewById(R.id.buttonMinus);
        MaterialButton btnDiv = findViewById(R.id.buttonDiv);

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

        //LISTENERS
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

        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantalla.append("*");
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantalla.append("-");
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantalla.append("/");
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strScreen = pantalla.getText().toString();

                boolean strOK = StringVerifier.isGoodString(strScreen);

                if(!strOK){ //Cambio la estrategia y muestro '0' (+ toast) en caso de error para poder abordar operaciones de resta..
                    pantalla.setText("");
                    Toast.makeText(getApplicationContext(), "Secuencia erronea. SIN OPERADORES UNARIOS!", Toast.LENGTH_SHORT).show();
                } else {

                    double result = 0.0d;

                    try{
                        result = calculadora.calculate(strScreen);

                        if(result % 1 != 0) pantalla.setText(String.valueOf(result));
                        else{
                            int intResult = (int) result;
                            pantalla.setText(String.valueOf(intResult));
                        }
                    }catch (ArithmeticException ex){
                        Toast.makeText(getApplicationContext(), "ERROR: División por 0. Vuelve a comenzar!", Toast.LENGTH_SHORT).show();
                        pantalla.setText("");
                    }
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


}