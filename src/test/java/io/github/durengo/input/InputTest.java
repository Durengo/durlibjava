package io.github.durengo.input;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class InputTest {
    private Input input;

    public InputTest() {
        input = new Input();
    }

    private void clearBuffer() {
        input.clearBuffer();
    }

    @Test
    public void integerInput1() {
        final int expected = 102;
        final String testString = "102";
        input.provideBuffer(testString);
        final int actual = input.gibi();

        input.clearBuffer();

        assertEquals(expected, actual);
    }

    @Test
    public void integerInput2() {
        final int expected = 102;
        final String testString = "102 1 2 3";
        input.provideBuffer(testString);
        final int actual = input.gibi();

        input.clearBuffer();

        assertEquals(expected, actual);
    }

    @Test
    public void integerInput3() {
        final int expected = 102;
        final String testString = "1e 02 asd1 2 3";
        input.provideBuffer(testString);
        final int actual = input.gibi();

        input.clearBuffer();

        assertNotEquals(expected, actual);
    }

    @Test
    public void stringInput1() {
        final String expected = "this is a string";
        final String testString = "this is a string";
        input.provideBuffer(testString);
        final String actual = input.gibsLine();

        input.clearBuffer();

        assertEquals(expected, actual);
    }

    @Test
    public void stringInput2() {
        final String expected = "this is a string";
        final String testString = "this is a string\nthis is a string\nthis is a string";
        input.provideBuffer(testString);
        final String actual = input.gibsLine();

        input.clearBuffer();

        assertEquals(expected, actual);
    }

    @Test
    public void charInput1() {
        final char expected = 'y';
        final char testString = 'y';
        input.provideBuffer(String.valueOf(testString));
        final char actual = input.gibc();

        input.clearBuffer();

        assertEquals(expected, actual);
    }

    @Test
    public void charInput2() {
        final String expected = "this is a string";
        final String testString = "this is a string\nthis is a string\nthis is a string";
        input.provideBuffer(testString);
        final String actual = input.gibsLine();

        input.clearBuffer();

        assertEquals(expected, actual);
    }
}
