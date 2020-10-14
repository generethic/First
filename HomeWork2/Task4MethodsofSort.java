package HomeWork2;

public class Task4MethodsofSort {
    public static void bubbleSort(int array[]) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-1; j++) {
                int temp = array[j];
                if(array[j]>array[j+1]) {
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
    public static void shakerSort(int array[]) {
        boolean swap = true;
        int start = 0;
        int end = array.length;
        while(swap) {
            swap = false;
            for (int i = 0; i < end-1; i++) {
                if(array[i]>array[i+1]) {
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    swap = true;
                }
            }
            if(swap==false) {
                break;
            }

            swap = false;
            end -=1;

            for (int i = end-1; i >=start ; i--) {
                if(array[i]>array[i+1]) {
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    swap = true;
                }
            }
        }
    }
}
