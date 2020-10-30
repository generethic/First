package HomeWork5;

public class Ex {
    public static void main(String[] args) {
        String word = "abcd,abcdef,abc";
        System.out.println(find(word,"abc"));
    }
    public static long find(String text, String word) {
        text = text.toLowerCase();
        word = word.toLowerCase();
        long result = 0;
        int enter = text.indexOf(word);
        System.out.println(enter);
        String txt =text.substring(enter,word.length()+1);
        if(!txt.equals(word)) {
            System.out.println(false);
        }
        while (enter != -1) {
            enter = text.indexOf(word, enter + word.length());
            result++;
        }
        return result;
    }
}
