package com.example.calculadoraa;

/**
 * @author dioga
 * @version 1.0
 * @since 2024
 */
public class Calculadora {

    /**
     * Función recursiva para el cálculo de aritmética básica de una calculadora (suma., multiplicación)
     * @param cadena texto cuyos caracteres integran una operación, encadenada o simple de suma o multiplicación
     * @return Retorno de tipo numèrico con el resultado de la operacion aritmética (SIN OPERADORES UNARIOS)
     */
    public double calculate(String cadena) throws ArithmeticException {
        //Estrategia: evaluar de derecha a izquierda

        // Sumas y restas primero (derecha a izquierda)
        if (cadena.contains("+")) {

            String[] partes = cadena.split("\\+", 2);
            String izquierda = partes[0];
            String derecha = partes[1];
            return calculate(izquierda) + calculate(derecha);
        } else if (cadena.contains("-")) {

            String[] partes = cadena.split("\\-", 2);
            String izquierda = partes[0];
            String derecha = partes[1];
            return calculate(izquierda) - calculate(derecha);
        }

        // Multiplicaciones y divisiones (derecha a izquierda)
        if (cadena.contains("*")) {

            String[] partes = cadena.split("\\*", 2);
            String izquierda = partes[0];
            String derecha = partes[1];
            return calculate(izquierda) * calculate(derecha);

        } else if (cadena.contains("/")) {

            String[] partes = cadena.split("\\/", 2);
            String izquierda = partes[0];
            String derecha = partes[1];

            // Verificación de división por cero
            if (calculate(derecha) == 0) {
                throw new ArithmeticException("ERROR: División por 0");
            }
            return calculate(izquierda) / calculate(derecha);
        }

        // CASO BASE: la cadena es un número
        return Double.parseDouble(cadena);
    }
}


