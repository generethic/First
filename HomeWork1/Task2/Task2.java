package HomeWork1.Task2;

public class Task2 {
    public static void main(String[] args) {
        int a = 8;
        int b = 2;
        System.out.println(5 + b / a); // 5+2/8 -> 5+0, т.к. типы int -> результат 5
        System.out.println((5 + b) / a); // (5+2)/8, т.к. int -> результат 0
        System.out.println((5 + b++) / a); //(5+2)/8, форма инкремента постфиксная, 7/8 -> 0
        System.out.println((5 + b++) / --a); //(5+2)/7,форма инкремента постфиксная,декремента префиксная, 7/7 ->1
        System.out.println((5 * 2 >> b++) / --a); // 5*2 = 10 (1010 в двоичной системе),затем смещение на 2 единицы(2),деление на 7 ->0
        int d = 8;
        int e = 2;
        System.out.println((5 + 7 > 20 ? 68 : 22 * 2 >> e++) / --d); //22*2 = 44,смещение на 2 знака, это 11,тернарный оператор,12<20, поэтому 11, деление на 11/8=8
        //System.out.println((5 + 7 > 20 ? 68 >= 68 : 22 * b >> b++) / --a); //будет ошибка компиляции, т.к. при выполнении ветвления, в качестве истины будет true,а в качестве лжи, получится число, и опять же, деление на число
        System.out.println(6 - 2 > 3 && 12 * 12 <= 119); // true&&false ->false
        System.out.println(true && false); // true&&false ->false
    }
}
