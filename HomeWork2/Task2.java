package HomeWork2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task2 {
    public static void main(String[] args) throws IOException {
        int[] array = new int[10];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(reader.readLine());
        }
        printArray1(array);
        printArray2(array);
        printArray3(array);
        printArray4(array);
        printEvenNumbers(array);
        printReverse1(array);
        printReverse2(array);
        printReverse3(array);
        printReverse4(array);
    }
    public static void printArray1(int[] array) {
        int count = 0;
        int size = array.length;
        do {
            System.out.print(array[count]+" ");
            count++;
        }while (count<size);
    }
    public static void printArray2(int array[]) {
        int count = 0;
        int size = array.length;
        while(count<size) {
            System.out.print(array[count]+" ");
            count++;
        }
    }
    public static void printArray3(int array[]) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }
    public static void printArray4(int array[]) {
        for (int z : array) {
            System.out.print(z+" ");
        }
    }
    public static void printEvenNumbers(int array[]) {
        for (int i = 0; i < array.length; i++) {
            if(array[i]%2==0) {
                System.out.print(array[i]+" ");
            }
        }
    }
    public static void printReverse1(int array[]) {
        int count = array.length-1;
        do {
            System.out.print(array[count]+" ");
            count--;
        }while (count>=0);
    }
    public static void printReverse2(int array[]) {
        int count = array.length-1;
        while(count>=0) {
            System.out.print(array[count]+" ");
            count--;
        }
    }
    public static void printReverse3(int array[]) {
        for (int i = array.length-1; i >= 0; i--) {
            System.out.print(array[i]+" ");
        }
    }
    public static void printReverse4(int array[]) {
        int[] array1 = new int[array.length];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = array[array.length-1-i];
        }
        for (int q : array1) {
            System.out.print(q+" ");
        }
    }
}
