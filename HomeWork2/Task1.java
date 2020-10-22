package HomeWork2;

import java.io.*;
import java.util.LinkedList;

public class Task1 {
    public static void main(String[] args) throws Exception {
        int anInt = Integer.parseInt(args[0]);                   //Когда делал ДЗ изначально, видимо невнимательно прочитал, поэтому добавил код, что бы использовать аргумент к исполняемой программе.
//        multiply(anInt);
//        multiplyTheSecond(anInt);
//        multiplyTheThird();
        multiplyForth(35);                                //Опять же, когда делал ДЗ, видимо невнимательно прочитал и не увидел, что есть разные значения (отрицательные сделать не получилось)
//        multiplyFifth();
    }

    public static void multiply(int a) {
        int result = 1;
        int count = 1;
        if (a == 0) {
            return;
        }
        if (a == 1) {
            System.out.println(count + " * " + a + " = " + (count * a));
            return;
        }

        System.out.print(count);
        while (count < a) {
            ++count;
            System.out.print(" * " + count);
            result = result * count;
        }
        System.out.println(" = " + result);
    }

    public static void multiplyTheSecond(int a) {
        int result = 1;
        char[] array1 = String.valueOf(a).toCharArray();
        int size = array1.length;
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            int number = a % 10;
            array[i] = number;
            a = a / 10;
        }
        for (int i = 0; i < array.length; i++) {
            result *= array[i];
        }
        for (int i = array.length-1; i >= 0 ; i--) {
            if(i==0) {
                System.out.print(array[i]+" = ");
                continue;
            }
            System.out.print(array[i]+" * ");
        }
        System.out.println(result);
    }

    public static void multiplyTheThird() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double number = Double.parseDouble(reader.readLine());
        int row = Integer.parseInt(reader.readLine());
        if (row > 0 & row % 1 == 0) {
            double result = 1;
            for (int i = 1; i <= row; i++) {
                result = result * number;

            }
            if (result % 1 == 0) {
                System.out.println((int) result);
            } else {
                System.out.println(result);
            }
        } else {
            System.out.println();
        }
    }

    public static void multiplyForth(int factor) {
        long a = 1;
        long valueBeforeOverflow = 0;
        long overflowingValue = 0;
        if(factor>0) {
            while (a > 0) {
                a *= factor;
                if (a > 0) {
                    valueBeforeOverflow = a;
                }
                if (a < 0) {
                    overflowingValue = a;
                    break;
                }
            }
        }
//        if(factor<0) {
//            while (true) {
//                LinkedList<Long> listMax = new LinkedList<>();
//                LinkedList<Long> listMin = new LinkedList<>();
//                a *= factor;
//                System.out.println(a);
//                if(a>0 && a<Long.MAX_VALUE) {
//                    listMax.add(a);
//                    for (int i = 0; i < listMax.size()-1; i++) {
//                        if(listMax.get(i+1)>listMax.get(i)) {
//                            valueBeforeOverflow = a;
//                        }
//                    }
//                }
//                if(a<0 && a>Long.MIN_VALUE) {
//                    if(a>0 && a<Long.MIN_VALUE) {
//                        listMin.add(a);
//                        for (int i = 0; i < listMin.size()-1; i++) {
//                            if(listMin.get(i+1)<listMin.get(i)) {
//                                overflowingValue = a;
//                            }
//                        }
//                    }
//                }
//            }
//        }
        System.out.println("Число до переполнения "+valueBeforeOverflow);
        System.out.println("Число после переполнения "+overflowingValue);
    }

    public static void multiplyFifth() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("");
            for (int j = 2; j <= 5; j++) {
                    System.out.print(j + " x " + i + " = " + j * i + "\t"+"    ");
            }
        }
        System.out.println("");
        for (int i = 1; i <= 10; i++) {
            System.out.println("");
            for (int j = 6; j <= 9; j++) {
                System.out.print(j + " x " + i + " = " + j * i + "\t"+"    ");
            }
        }
    }
}