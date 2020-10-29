package HoweWork4;

import java.util.Arrays;
import java.util.Comparator;

public class DataMain<T> {
    public static void main(String[] args) {
        //был взят чужой код для проверки работоспобности класса DataContainer
        int index;

        Integer[] data = new Integer[]{1, 2, 3, null, null, null,7,25};
        DataContainer<Integer> collection = new DataContainer<>(data);

        System.out.println("Исходный массив: " + Arrays.toString(data));
        index = collection.add(653);
        System.out.println("Добавлено под номером: " + index);
        index = collection.add(37);
        System.out.println("Добавлено под номером: " + index);
        System.out.println("Новый массив: " + Arrays.toString(collection.getItems()));
        System.out.println("Объект под номером " + index + ": " + collection.get(index));

        Integer[] data1 = new Integer[]{};
        DataContainer<Integer> collection2 = new DataContainer<>(data1);

        index = collection2.add(9999);
        System.out.println("Новый массив: " + Arrays.toString(collection2.getItems()));
        System.out.println("Объект под номером " + index + ": " + collection2.get(index));
        System.out.println("Объект под номером " + 1 + ": " + collection2.get(1));

        Integer[] data2 = new Integer[]{1, 2, null, 3, 777};
        DataContainer<Integer> ds2 = new DataContainer<>(data2);
        System.out.println("Исходный массив: " + Arrays.toString(data2));
        index = 2;
        System.out.println("Результат удаления объекта с индексом " + index + ": " + ds2.delete(index));
        System.out.println("Новый массив: " + Arrays.toString(ds2.getItems()));

        Integer[] data3 = new Integer[]{1, 2, null, 3, null, 777, 3, null, null, 5};
        DataContainer<Integer> ds3 = new DataContainer<>(data3);
        System.out.println("Исходный массив: " + Arrays.toString(data3));
        Integer item = 3;
        System.out.println("Результат удаления объекта " + item + ": " + ds3.delete(item));
        System.out.println("Новый массив: " + Arrays.toString(ds3.getItems()));
        System.out.println(ds3.toString());

        ds3.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
        System.out.println("Новый массив после сортировки: " + Arrays.toString(ds3.getItems()));
    }
}

