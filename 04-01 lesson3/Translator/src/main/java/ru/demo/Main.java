package ru.demo;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    private static final String MENU = "Выберите язык на который хотите перевести:\n1.Английский\n2.Русский\n3.Выход" ;
    public static int Choose;
    public static boolean exit = true;
    public static void main(String[] args) throws Exception {
        boolean isNumeric = true;
        while (exit){
            System.out.println(MENU);
            Scanner in = new Scanner(System.in);
            try {
                Choose = parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введено некоректное значение\nПожалуйста повторите еще раз!\n");
                isNumeric = false;
            }
            switch (Choose){
                case 1:
                    System.out.println("Введите текст для перевода:");
                    String Rutext = in.nextLine();
                    System.out.println("\nПеревод вашего текста:");
                    System.out.println(Translator.TranslatorToEng(Rutext) + "\n");
                    break;
                case 2:
                    System.out.println("Введите текст для перевода:");
                    String Entext = in.nextLine();
                    System.out.println("\nПеревод вашего текста:");
                    System.out.println(Translator.TranslateToRU(Entext) + "\n");
                    break;
                case 3:
                    exit = false;
                    System.out.println("Программа завершена.");
                    break;
                default:
                    if((Choose > 3 || Choose < 1) && isNumeric)
                        System.out.println("Введен текст\nПожалуйста введите порядковый номер!");
                    break;
            }
            isNumeric = true;
            Choose = 0;
        }

    }
}