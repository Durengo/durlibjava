package io.github.durengo.durlib.input;

import java.util.function.Function;
import java.util.function.Predicate;

public class Validation {
    public static boolean hasOnlyCharsOfType(String text, Predicate<Character> predicate) {
        return text.chars().allMatch(ch -> predicate.test((char) ch));
    }

    public static boolean hasOnlyCharsOfType(String text, char flag) {
        return text.chars().allMatch(ch -> ch == flag);
    }

    public static boolean hasCharsOfType(String text, Predicate<Character> predicate) {
        return text.chars().anyMatch(ch -> predicate.test((char) ch));
    }

    public static boolean hasCharsOfType(String text, char flag) {
        return text.chars().anyMatch(ch -> ch == flag);
    }

    public static int countCharsOfType(String text, Predicate<Character> predicate) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (predicate.test(text.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    public static int countCharsOfType(String text, char flag) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == flag) {
                count++;
            }
        }
        return count;
    }

    public static boolean onlyFirstIsCapital(String text) {
        if (text.charAt(0) != Character.toUpperCase(text.charAt(0)) || !hasOnlyCharsOfType(text, Character::isLetter)) {
            return false;
        }
        int count = 1;
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) == Character.toUpperCase(text.charAt(0))) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean canBePrimitive(String text, Function<String, T> parser) {
        try {
            parser.apply(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * String to Primitive Type
     *
     * @param <T>
     * @return
     */
    public static <T> T stringToPrimitiveType(String text, Function<String, T> parser) {
        try {
            return parser.apply(text);
        } catch (NumberFormatException e) {
            return (T) null;
        }
    }

}
