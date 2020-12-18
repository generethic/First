package Convert_Dates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
/*
Класс отвечает за работу с преобразование дат по заданным форматам
 */
public class EditingDates {
    public String[] edit(SimpleDateFormat simpleDateFormat, List<LocalDate> list) {
        String[] wordArray = new String[2];
        try {
            wordArray[0] = simpleDateFormat.format(ConvertArrayToDates.getFrom().parse(list.get(0).toString()));
            wordArray[1] = simpleDateFormat.format(ConvertArrayToDates.getFrom().parse(list.get(list.size()-1).toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return wordArray;
    }
    public String edit(String pattern1,String pattern2,String date) {
        String word = null;
        try {
            word = new SimpleDateFormat(pattern1).format(new SimpleDateFormat(pattern2, Locale.ENGLISH).parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return word;
    }
}
