package HomeWork2;

import java.util.Arrays;

public class Task4TestBubble {
    public static void main(String[] args) {
        int array1[] = {9,1,5,99,9,9};
        System.out.println("Массив до сортировки: " + Arrays.toString(array1));
        Task4MethodsofSort.bubbleSort(array1);
        System.out.println("Массив после сортировки: " + Arrays.toString(array1));
        int array2[] = {1,2,3,4,5,6};
        System.out.println("Массив до сортировки: " + Arrays.toString(array2));
        Task4MethodsofSort.bubbleSort(array2);
        System.out.println("Массив после сортировки: " + Arrays.toString(array2));
        int array3[] = {1,1,1,1};
        System.out.println("Массив до сортировки: " + Arrays.toString(array3));
        Task4MethodsofSort.bubbleSort(array3);
        System.out.println("Массив после сортировки: " + Arrays.toString(array3));
        int array4[] = {};
        System.out.println("Массив до сортировки: " + Arrays.toString(array4));
        Task4MethodsofSort.bubbleSort(array4);
        System.out.println("Массив после сортировки: " + Arrays.toString(array4));
    }
}
