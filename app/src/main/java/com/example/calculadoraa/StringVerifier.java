package com.example.calculadoraa;

/**
 * @author dioga
 * @version 1.0
 * @since 2024
 *
 * Clase helper para determinar si una cadena es candidata a
 * poder ser procesada por el método 'calculate' de la clase 'Calculadora'
 * OBJETIVO: aligerar la lógica en el MainActivity..
 */
public class StringVerifier {

    /**
     *
     * @param cadena Encadenación de operaciones aritméticas (suma, resta, mult, div)
     * @return Verdadero / Falso en función de si es una cadena 'tratable'
     * por el método 'calculate' de la clase 'Calculadora'
     *
     */
    public static boolean isGoodString(String cadena) {


        if (cadena.equals("")) return false; //Empty string
        if (cadena.contains("++")) return false;
        if (cadena.contains("**")) return false;
        if (cadena.contains("--")) return false;
        if (cadena.startsWith("-")) return false;  // Not unary implementation for now..

        //Containing no digits case
        if(cadena.matches("^[^0-9]*$")) return false;

        //Containing not allowed digits
        for(int i = 0; i<cadena.length(); i++){
            if( (cadena.charAt(i) != '+' &&
                    cadena.charAt(i) != '*' &&
                    cadena.charAt(i) != '-' &&
                    cadena.charAt(i) != '/' ) && !Character.isDigit(cadena.charAt(i))){

                return false;
            }
        }

        return true;
    }
}
