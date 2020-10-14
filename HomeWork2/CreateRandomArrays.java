package HomeWork2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class CreateRandomArrays {
    public static void main(String[] args) throws IOException {
//        int array[] = createRandomArray();
//        System.out.println(Arrays.toString(array));
//          int array[] = createByConsoleArray();
//          System.out.println(Arrays.toString(array));
    }
    public static int[] createRandomArray() {
        Random random = new Random();
        int number = random.nextInt();
        while (number < 0)
        {
            number = random.nextInt();
        }


        int array[] = new int[15];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }
    public static int[] createByConsoleArray() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int array[] = new int[15];
        for (int j = 0; j < array.length; j++) {
            int i = Integer.parseInt(reader.readLine());
            array[j] = i;
        }
        return array;
    }
}
