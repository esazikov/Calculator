package ru.javamentor;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IncorrectDigitException, NotOperandException {

        int answer;
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String[] strings = string.split(" ");
        Digit firstElement = makeDigit(strings[0]);
        Digit secondElement = makeDigit(strings[2]);

        if (firstElement.getType() != secondElement.getType()) {
            throw new IncorrectDigitException("Одно из введенных вами чисел арабское, а другое - римское.");
        }

        switch (strings[1]) {
            case "+" :
                answer = firstElement.getValue() + secondElement.getValue();
                break;
            case "-" :
                answer = firstElement.getValue() - secondElement.getValue();
                break;
            case "*" :
                answer = firstElement.getValue() * secondElement.getValue();
                break;
            case "/" :
                answer = firstElement.getValue() / secondElement.getValue();
                break;
            default :
                throw new NotOperandException ("Вы ввели неправильный знак операции.");
        }
        if (firstElement.getType() == Type.Rome) {
            System.out.println(arabicToRoman(answer));
        } else {
            System.out.println(answer);
        }
    }

    private static Digit makeDigit (String s) throws IncorrectDigitException {

        int digit;
        Type type;

        try {
            digit = Integer.parseInt(s);
            type = Type.Arabic;
        } catch (NumberFormatException e) {
            try {
                digit = romanToArabic(s);
                type = Type.Rome;
            } catch (IllegalArgumentException e1) {
                throw new IncorrectDigitException("Вы ввели не числа.");
            }
        }

        if (digit < 1 || digit > 10) {
            throw new IncorrectDigitException("Числа должны быть в диапазоне от 1 до 10 или от I до X.");
        }

        return new Digit(digit, type);
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    public static String arabicToRoman(int number) {

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

}
