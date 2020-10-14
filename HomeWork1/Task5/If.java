package HomeWork1.Task5;

import java.util.Scanner;

public class If {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String name1 = "Вася";
        String name2 = "Анастасия";
        if(name.equals(name1)) {
            System.out.println("Привет!");
            System.out.println("Я тебя так долго ждал");
        }
        if(name.equals(name2)) {
            System.out.println("Я тебя так долго ждал");
        }
        if(name!=name1) {
            if (name != name2) {
                System.out.println("Добрый день, а вы кто?");
            }
        }
    }
}
