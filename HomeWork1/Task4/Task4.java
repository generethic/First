package HomeWork1.Task4;

public class Task4 {
    public static void main(String[] args) {
        sleepIn(true,false);
    }

    public static boolean sleepIn(boolean weekday, boolean vacation) {
        if (weekday == false || vacation == true) {
            System.out.println("Можно спать дальше");
            return true;
        }
        else {
            System.out.println("Необходимо идти на работу");
            return false;
        }
    }
}
