package com.example.calculadoraa;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *
 * @author dioga
 * @version 1.0
 * @since 2024
 *
 * Se han ajustado los 'assertequals' para poder trabajar con double
 * y un 3er parámetro 'delta' (desviación entre flotantes de doble precisión)
 */
public class MainActivityTest {


    private Calculadora calc;

    @Before
    public void setup(){
        calc = new Calculadora();
    }

    @Test
    public void testAdd2operands(){
        double total = calc.calculate("5+3");
        assertEquals("+X operations not working properly", 8.0, total, 0.0);
    }


    @Test
    public void testAdd1operand(){
        double total = calc.calculate("4+3+1");
        assertEquals("X + Y operations not working properly", 8, total, 0.0);
    }

    @Test
    public void testMult2Operands(){
        double total = calc.calculate("4*2");
        assertEquals("4*X operations not working properly", 8, total, 0.0);
    }

    @Test
    public void testSumWithZero(){
        double total = calc.calculate("0+1");
        assertEquals("Operations with zero not working properly", 1, total, 0.0);
    }

    @Test
    public void testBigNumbers(){
        double total = calc.calculate("1000000+2000000");
        assertEquals("Big operations not working properly", 3000000, total, 0.0);
    }

    //Hard testing 4 multiplications

    @Test
    public void simpleMult(){
        double total = calc.calculate("2*3");
        assertEquals("Simple mult operations not working properly", 6, total, 0.0);
    }

    @Test
    public void chainedMult(){
        double total = calc.calculate("1*2*8");
        assertEquals("Chained mult operations not working properly", 16, total, 0.0);
    }

    @Test
    public void multBeforeSum(){
        double total = calc.calculate("2*2+3");
        assertEquals("Simple mult before sum operations not working properly", 7, total, 0.0);
    }

    @Test
    public void sumBeforeMult(){
        double total = calc.calculate("3+2*2");
        assertEquals("Simple sum before mult operations not working properly", 7, total, 0.0);
    }

    @Test
    public void sumMultSum(){
        double total = calc.calculate("3+2*2+4");
        assertEquals("Mult intercalated to two sum operations not working properly", 11, total, 0.0);
    }

    // Substractions TEST

    @Test
    public void SimpleSubstraction(){
        double total = calc.calculate("12-2");
        assertEquals("Simple substraction not working properly", 10, total, 0.0);
    }

    @Test
    public void prioritySumOverSubstraction(){
        double total = calc.calculate("12-5*2");
        assertEquals("Substarctions with Mult not working properly", 2, total, 0.0);
    }

    @Test
    public void substractionFollowedSum(){
        double total = calc.calculate("12-5+2");
        assertEquals("Substarctions with Sum operations not working properly", 9, total, 0.0);
    }
    @Test
    public void priorityMultOverSumFollowedSubstractionChained(){
        double total = calc.calculate("10+4-7*2");
        assertEquals("Chained sum and subs with prior mult operations not working properly", 0, total, 0.0);
    }

    @Test
    public void multSumSubstChained(){
        double total = calc.calculate("1*2+4-1");
        assertEquals("Chained sum and subs with prior mult operations not working properly", 5, total, 0.0);
    }

    // Division TEST

    @Test
    public void simpleDivision(){
        double total = calc.calculate("4/2");
        assertEquals("Simple division operations not working properly", 2, total, 0.0);
    }

    @Test
    public void priorityDivisionOverSum1(){
        double total = calc.calculate("4/2+1");
        assertEquals("Priorized division over sum operations not working properly", 3, total, 0.0);
    }

    @Test
    public void priorityDivisionOverSum2(){
        double total = calc.calculate("4+2/2");
        assertEquals("Priorized division over sum operations not working properly", 5, total, 0.0);
    }

    @Test
    public void comboWithDivision1(){
        double total = calc.calculate("4+2/2*3");
        assertEquals("Chained priority operations not working properly", 7, total, 0.0);
    }


    @Test
    public void comboWithDivision2(){
        double total = calc.calculate("4+2*2/4");
        assertEquals("Chained priority operations not working properly", 5, total, 0.0);
    }

    //Testing if the Arithmethic exception is reached or not..
    @Test(expected = ArithmeticException.class)
    public void zeroDivisionError(){
        double total = calc.calculate("10/0");
    }










}