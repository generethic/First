package HoweWork4;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class DataContainer<T> {
    private int position;
    private T[] data;

    public DataContainer() {
        data = (T[]) new Object[0];
    }
    public DataContainer(T[] data) {
        this.data = data;
    }

    private int indexOfNull() {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                position = i;
                break;
            }
        }
        return position;
    }

    private boolean hasEmpty() {
        int count = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                count++;
            }
        }
        return count > 0;
    }

    public int add(T item) {
        if (hasEmpty()) {
            position = indexOfNull();
            if (item == null) {
                return -1;
            }
            data[position] = item;
            return position;
        } else {
            data = Arrays.copyOf(data, data.length + 1);
            data[data.length - 1] = item;
            return data.length - 1;
        }
    }

    public T get(int index) {
        if (index >= data.length) {
            return null;
        }
        return data[index];
    }

    public T[] getItems() {
        return data;
    }

    void print() {
        System.out.println(Arrays.toString(data));
    }

    boolean delete(int index) {
        if (index >= data.length) {  //добавлена проверка, что бы небыло ошибки при удалении ошибочного индекса
            return false;
        }
        biasIndex(index);
        return true;
    }

    private void biasIndex(int index) {
        if (index == data.length - 1) {
            data = Arrays.copyOf(data, data.length - 1);
            return;
        } else if (index < data.length - 1) {
            for (int i = index; i < data.length - 1; i++) {
                data[index] = data[index + 1];
                index++;
            }
            data = Arrays.copyOf(data, data.length - 1);
        }
    }

    boolean delete(T item) {
        int find = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i]==item) {   //equals для сравнения ссылок, '==' для сравнения содержимого
                find = i;
            }
        }
        biasIndex(find);
        return true;
    }
    public void addTest(T item) {
        data = Arrays.copyOf(data, data.length + 1);
        data[data.length-1] = item;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < data.length; i++) {
            if(data[i]!=null) {
                if(flag) {
                    stringBuilder.append(" ");
                }
                flag = true;
                stringBuilder.append(data[i]);
            }
        }
        return String.valueOf(stringBuilder);
    }
    void sort(Comparator<T> comparator) {
        int n = data.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (comparator.compare(data[j], data[j+1]) > 0) {
                    T temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                }
    }

    static void sort(DataContainer dataContainer,Comparator comparator) {
        dataContainer.sort(comparator);
    }
//    private void swap(T val1,T val2) {
//        T temp = val1;
//        val1 = val2;
//        val2 = temp;
//    }

//    static <T extends Comparable<T>> void sort(DataContainer<T> dataContainer) {
//                int length = dataContainer.getItems().length;
//                for (int i = 0; i < length-1; i++) {
//                    for (int j = 0; j < length-i-1; j++) {
//                        if(dataContainer.getItems()[j].compareTo(dataContainer.getItems()[j+1])>0) {
//                        dataContainer.swap(dataContainer.getItems()[j],dataContainer.getItems()[j+1]);
//                }
//            }
//        }
//    }
}
