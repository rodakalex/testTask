import java.io.*;

import static java.lang.Integer.parseInt;

public class Main {
    private static boolean isNumeric;
    
    public static void main(String[] args) throws IOException {
        String[] expressionLine = readLineTwoNumbers();
        if (checkLine(expressionLine)) {
            int result = calcExpression(expressionLine);
            if (isNumeric) {
                System.out.println(result);
            } else {
                try {
                    System.out.println(RomanArabicConverter.arabicToRoman(result));
                } catch (IllegalArgumentException e) {
                    System.err.println("throws Exception");
                }
            }
        } else {
            System.err.println("throws Exception");
        }
    }

    private static int calcExpression(String[] expressionLine) {
        int x, y;
        if (isNumeric) {
            x = Integer.parseInt(expressionLine[0]);
            y = Integer.parseInt(expressionLine[2]);
        } else {
            x = RomanArabicConverter.romanToArabic(expressionLine[0]);
            y = RomanArabicConverter.romanToArabic(expressionLine[2]);
        }

        switch (expressionLine[1]) {
            case ("+"):
                return x + y;
            case ("-"):
                return x - y;
            case ("*"):
                return x * y;
            case ("/"):
                return x / y;
            default:
                return 0;
        }
    }

    static String[] readLineTwoNumbers() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String inputLine = reader.readLine();
        return inputLine.split(" ");
    }

    static boolean checkLine(String[] expressionLine) {
        // Проверка на валидное выражение
        return expressionLine.length == 3 && isValidNumbers(expressionLine);
    }

    static boolean isValidNumbers(String[] expressionLine) {
        // Проверка на то что это либо 2 числа, либо на то, что это 2 римских символа
        isNumeric = isValidNumeric(expressionLine);
        return (isNumeric) ||
                (RomanArabicConverter.isRome(expressionLine[0]) && RomanArabicConverter.isRome(expressionLine[2]));
    }

    static boolean isValidNumeric(String[] numbers) {
        try {
            int x = parseInt(numbers[0]);
            int y = parseInt(numbers[2]);
            if (x <= 10 && y <= 10) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
