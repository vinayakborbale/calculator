package com.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculatorApplicationTests {

	@Test
	void contextLoads() {
	}


	@Autowired
    private CalculatorService calculatorService;

    @Test
    void testAddEmptyString() {
        int result = calculatorService.add("");
        System.out.println("Result of adding empty string: " + result);
        assertEquals(0, result);
    }

    @Test
    void testAddSingleNumber() {
        int result = calculatorService.add("1");
        System.out.println("Result of adding single number '1': " + result);
        assertEquals(1, result);
    }

    @Test
    void testAddTwoNumbers() {
        int result = calculatorService.add("1,5");
        System.out.println("Result of adding two numbers '1,5': " + result);
        assertEquals(6, result);
    }

    @Test
    void testAddMultipleNumbers() {
        int result = calculatorService.add("1,2,3,4");
        System.out.println("Result of adding multiple numbers '1,2,3,4': " + result);
        assertEquals(10, result);
    }

    @Test
    void testAddWithNewLineDelimiter() {
        int result = calculatorService.add("1\n2,3");
        System.out.println("Result of adding with new line delimiter '1\\n2,3': " + result);
        assertEquals(6, result);
    }

    @Test
    void testAddWithCustomDelimiter() {
        int result = calculatorService.add("//;\n1;2");
        System.out.println("Result of adding with custom delimiter '//;\\n1;2': " + result);
        assertEquals(3, result);
    }

    @Test
    void testAddNegativeNumbersThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.add("1,-2,3,-4");
        });
        System.out.println("Exception message for negative numbers: " + exception.getMessage());
        assertEquals("Negative numbers not allowed: [-2, -4]", exception.getMessage());
    }

}
