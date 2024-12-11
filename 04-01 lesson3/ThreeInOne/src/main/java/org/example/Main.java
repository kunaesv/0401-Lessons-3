package org.example;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import static java.lang.Integer.parseInt;

public class Main {
    static final String MENU = "\nВыберите номер действия:\n1.Картинка собаки \n2.Факт о кошках \n3.Шутка про программирование\n4.Завершить программу\n";
    public static int Choose;
    public static boolean exit = true;
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        boolean isNumeric = true;
        while (exit){
            System.out.println(MENU);
            Scanner in = new Scanner(System.in);
            try {
                Choose = parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введено некоректное значение\nПожалуйста повторите еще раз!");
                isNumeric = false;
            }
            switch (Choose){
                case 1:
                    Dog.Image();
                    break;
                case 2:
                    Cat.Facts();
                    break;
                case 3:
                    Joke.Text();
                    break;
                case 4:
                    exit = false;
                    System.out.println("Программа завершена.");
                    break;
                default:
                    if((Choose > 4 || Choose < 1) && isNumeric)
                        System.out.println("Введено неверное значение\nПожалуйста повторите еще раз!");
                    break;
            }
            isNumeric = true;
            Choose = 0;
        }

    }
}