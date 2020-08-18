package ru.javamentor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IncorrectDigitException, NotOperandException {

        int answer;
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String[] strings = string.split(" ");
        int firstElement = makeDigit(strings[0]);
        int secondElement = makeDigit(strings[2]);

        switch (strings[1]) {
            case "+" :
                answer = firstElement + secondElement;
                break;
            case "-" :
                answer = firstElement - secondElement;
                break;
            case "*" :
                answer = firstElement * secondElement;
                break;
            case "/" :
                answer = firstElement / secondElement;
                break;
            default :
                throw new NotOperandException ("Вы ввели неправильный знак операции.");
        }
        System.out.println(answer);
    }

    private static int makeDigit (String s) throws IncorrectDigitException {

        int digit;

        try {
            digit = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            try {
                digit = RomeDigit.valueOf(s).getValue();
            } catch (IllegalArgumentException e1) {
                throw new IncorrectDigitException("Вы ввели не числа.");
            }
        }

        if (digit < 1 || digit > 10) {
            throw new IncorrectDigitException("Числа должны быть в диапазоне от 1 до 10.");
        }

        return digit;
    }
}
