package HoweWork4;

import java.util.Comparator;

public class DataMain<T> {
    public static void main(String[] args) {
        DataContainer<Integer> collection = new DataContainer<>();
//        for (int i = 0; i <= 10; i++) {
//            collection.add(i*10);
//        }
//        collection.print();
//        collection.delete(10);
//        collection.print();
//        collection.delete(new Integer(90));
//        collection.print();
        collection.addTest(50);
        collection.addTest(null);
        collection.addTest(40);
        collection.addTest(null);
        collection.addTest(30);
        collection.addTest(20);
        collection.addTest(10);
        collection.add(15);
        collection.add(25);
        System.out.println(collection.toString());
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        collection.sort(comp);
        System.out.println(collection.toString());
    }
}

