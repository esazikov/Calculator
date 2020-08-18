package ru.javamentor;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<String> romeDigit = new ArrayList<>() ;

    public static void main(String[] args) throws IncorrectDigitException, NotOperandException {

        romeDigit.add("I");
        romeDigit.add("II");
        romeDigit.add("III");
        romeDigit.add("IV");
        romeDigit.add("V");
        romeDigit.add("VI");
        romeDigit.add("VII");
        romeDigit.add("VIII");
        romeDigit.add("IX");
        romeDigit.add("X");
        romeDigit.add("XI");
        romeDigit.add("XII");
        romeDigit.add("XIII");
        romeDigit.add("XIV");
        romeDigit.add("XV");
        romeDigit.add("XVI");
        romeDigit.add("XVII");
        romeDigit.add("XVIII");
        romeDigit.add("IX");
        romeDigit.add("XX");


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
            System.out.println(romeDigit.get(answer - 1));
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
                digit = romeDigit.indexOf(s) + 1;
                type = Type.Rome;
            } catch (IllegalArgumentException e1) {
                throw new IncorrectDigitException("Вы ввели не числа.");
            }
        }

        if (digit < 1 || digit > 10) {
            throw new IncorrectDigitException("Числа должны быть в диапазоне от 1 до 10.");
        }

        return new Digit(digit, type);
    }
}
