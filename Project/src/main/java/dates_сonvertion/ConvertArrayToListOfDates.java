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

    public List<LocalDate> getTotalDates(boolean flag,LocalDate...dates) {
        return reformatDates(flag,dates);
    }

    private List<LocalDate> reformatDates(boolean flag, LocalDate...date) {
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
