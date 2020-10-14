package HomeWork1.Task6;

public class Task6 {
    public static void main(String[] args) {
        int[] array = {0,1,2,3,4,5,6,7,8,9};
        System.out.println(createPhoneNumber(array));
    }
    static String createPhoneNumber(int array[]) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("(");
        for (int i = 1; i < 10; i++) {
            if(i==4) {
                stringBuilder1.append(") ");
            }
            if(i==7) {
                stringBuilder1.append("-");
            }
            stringBuilder1.append(array[i]);
        }
        stringBuilder1.append(array[0]);
        String s = String.valueOf(stringBuilder1);
        return s;
    }
}
