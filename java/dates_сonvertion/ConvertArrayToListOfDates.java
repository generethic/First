package dates_сonvertion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/*
    Класс отвечает за преобразование дат, переданных в виде массива из одного или двух элементов (начало-конец) в лист дат
 */
public class ConvertArrayToListOfDates {
    //метод, который согласно переданной перееменной flag формирует List с диапазоном конкретных дат, либо заполняет даты от ранеей к поздней
    public static List<LocalDate> getTotalDates(boolean flag, LocalDate...date) {
        List<LocalDate> list;
        List<LocalDate> dateList = new ArrayList<>();
        list = new ArrayList<>(date.length);
        list.addAll(Arrays.asList(date));
        Collections.sort(list);
        if(flag) {
            LocalDate start = list.get(0);
            LocalDate end = list.get(list.size() - 1);
            long days = ChronoUnit.DAYS.between(start, end);
            for (int i = 0; i <= days ; i++) {
                dateList.add(start.plusDays(i));
            }
        }
        else {
            dateList = list;
        }
        return dateList;
    }
}
