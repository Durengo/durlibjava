package io.github.durengo.durlib.input;

import io.github.durengo.durlib.input.Validation;
import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.*;

public class ParseTest {
    @Test
    public void testHasOnlyCharsOfTypePredicate() {
        String text = "Hello World!";
        Predicate<Character> punctuationPredicate = ch -> !Character.isLetterOrDigit(ch);
        Predicate<Character> digitPredicate = Character::isDigit;
        assertFalse(Validation.hasOnlyCharsOfType(text, punctuationPredicate));
        assertFalse(Validation.hasOnlyCharsOfType(text, digitPredicate));

    }

    @Test
    public void testHasOnlyCharsOfTypeChar() {
        String text = "aaa";
        char a = 'a';
        char b = 'b';
        assertTrue(Validation.hasOnlyCharsOfType(text, a));
        assertFalse(Validation.hasOnlyCharsOfType(text, b));
    }

    @Test
    public void testHasCharsOfTypePredicate() {
        String text = "12345";
        Predicate<Character> letterPredicate = Character::isLetter;
        Predicate<Character> digitPredicate = Character::isDigit;
        assertTrue(Validation.hasCharsOfType(text, digitPredicate));
        assertFalse(Validation.hasCharsOfType(text, letterPredicate));
    }

    @Test
    public void testHasCharsOfTypeChar() {
        String text = "Hello World!";
        char a = 'a';
        char b = 'b';
        assertTrue(Validation.hasCharsOfType(text, 'l'));
        assertFalse(Validation.hasCharsOfType(text, a));
        assertFalse(Validation.hasCharsOfType(text, b));
    }

    @Test
    public void testCountCharsOfTypePredicate() {
        String text = "The quick brown fox jumps over the lazy dog.";
        Predicate<Character> vowelPredicate = c -> "aeiouAEIOU".indexOf(c) >= 0;
        assertEquals(11, Validation.countCharsOfType(text, vowelPredicate));
    }

    @Test
    public void testCountCharsOfTypeChar() {
        String text = "Mississippi";
        assertEquals(4, Validation.countCharsOfType(text, 's'));
        assertEquals(4, Validation.countCharsOfType(text, 'i'));
    }

    @Test
    public void testOnlyFirstIsCapital_ValidInput_ReturnsTrue() {
        assertTrue(Validation.onlyFirstIsCapital("John"));
        assertTrue(Validation.onlyFirstIsCapital("Alice"));
        assertTrue(Validation.onlyFirstIsCapital("Mary"));
    }

    @Test
    public void testOnlyFirstIsCapital_InvalidInput_ReturnsFalse() {
        assertFalse(Validation.onlyFirstIsCapital("jane"));
        assertFalse(Validation.onlyFirstIsCapital("joHN"));
        assertFalse(Validation.onlyFirstIsCapital("  John"));
        assertFalse(Validation.onlyFirstIsCapital("!John"));
    }

    @Test
    public void testCanBePrimitiveValid() {
        assertTrue(Validation.canBePrimitive("42", Integer::parseInt));
    }

    @Test
    public void testCanBePrimitiveInvalid() {
        assertFalse(Validation.canBePrimitive("42a", Integer::parseInt));
    }

    @Test
    public void testStringToPrimitiveTypeValid() {
        Integer result = Validation.stringToPrimitiveType("42", Integer::parseInt);
        assertNotNull(result);
        assertEquals(Integer.valueOf(42), result);
    }

    @Test
    public void testStringToPrimitiveTypeInvalid() {
        Integer result = Validation.stringToPrimitiveType("42a", Integer::parseInt);
        assertNull(result);
    }
}
