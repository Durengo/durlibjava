package io.github.durengo.durlib.input;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

/**
 * A simple input class that waits and reads user input by using the scanner class.
 */
public class Input {
    private static Scanner sc = new Scanner(System.in);
    ;

    public static void provideBuffer(String buffer) {
        sc = new Scanner(buffer);
    }

    public static void resetBuffer(java.io.InputStream inputStream) {
        sc = new Scanner(inputStream);
    }

    public static void clearBuffer() {
        try {
            while (sc.hasNextLine()) {
                sc.nextLine();
//                sc.skip(".*");
            }
        } catch (Exception e) {
            return;
        }
    }

    /**
     * @return reads the input and tries to convert it to an integer.
     */
    public static int gibi() {
        int converted;
        while (true) {
            try {
                converted = Integer.parseInt(sc.next());
                return converted;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @param min minimum integer amount to be inputted.
     * @param max maximum integer amount to be inputted.
     * @return input as integer.
     */
    public static int gibi(int min, int max) {
        while (true) {
            int input = gibi();
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println("EXCEPTION! INPUT OUT OF RANGE: " + input + " in [MIN]{" + min + "}-[MAX]{" + max + "}\nTRY AGAIN");
            }
        }
    }

    /**
     * @return reads the input and tries to convert it to a double.
     */
    public static double gibd() {
        double converted;
        while (true) {
            try {
                converted = sc.nextDouble();
                return converted;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @param min minimum double amount to be inputted.
     * @param max maximum double amount to be inputted.
     * @return input as double.
     */
    public static double gibd(int min, int max) {
        while (true) {
            double input = gibd();
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println("EXCEPTION! INPUT OUT OF RANGE: " + input + " in [MIN]{" + min + "}-[MAX]{" + max + "}\nTRY AGAIN");
            }
        }
    }

    /**
     * @return reads the input and tries to convert it to a float.
     */
    public static float gibf() {
        float converted;
        while (true) {
            try {
                converted = sc.nextFloat();
                return converted;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @param min minimum float amount to be inputted.
     * @param max minimum float amount to be inputted.
     * @return input as float.
     */
    public static float gibf(int min, int max) {
        while (true) {
            float input = gibf();
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println("EXCEPTION! INPUT OUT OF RANGE: " + input + " in [MIN]{" + min + "}-[MAX]{" + max + "}\nTRY AGAIN");
            }
        }
    }

    /**
     * @return reads the input and tries to convert it to a String.
     */
    public static String gibs() {
        while (true) {
            try {
                String converted = sc.next();
                clearBuffer();
                return converted;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String gibsLine() {
        while (true) {
            try {
                String converted = sc.nextLine();
                return converted;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static char gibc() {
        while (true) {
            try {
                char converted = sc.nextLine().charAt(0);
                return converted;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static char gibc(int maximum_string_size) {
        while (true) {
            try {
                String c = sc.nextLine();
                if (c.length() > maximum_string_size) {
                    return '\n';
                }
                char converted = c.charAt(0);
                return converted;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @return reads the input and tries to return only if input is 'Y', 'y', 'N', 'n'
     */
    public static boolean getYesOrNo() {
        while (true) {
            char input = gibc(1);
            if (Objects.equals(input, 'Y') || Objects.equals(input, 'y')) {
                return true;
            }
            if (Objects.equals(input, 'N') || Objects.equals(input, 'n')) {
                return false;
            } else {
                System.out.println("EXCEPTION! INPUT MUST BE 'Y' OR 'N'");
            }
        }
    }
}
