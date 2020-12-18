package Dates;

import Other_Methods.BasicsAndPatterns;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
/*
    Класс отвечает за преобразование дат, переданных в виде массива из одного или двух элементов (начало-конец) в лист дат
 */
public class ConvertArrayToDates {
    private String starting;
    private String finishing;
    private LocalDate start;
    private LocalDate end;
    private static String pattern = BasicsAndPatterns.DATE_PATTERN_2.getName();
    private static SimpleDateFormat from = new SimpleDateFormat(pattern);
    protected static SimpleDateFormat getFrom() {
        return from;
    }

    public List<LocalDate> getTotalDates(String[] array) {
        return twoDate(array);
    }

    private List<LocalDate> twoDate(String[] array) {
        List<LocalDate> list;
        if(array.length==1) {
            starting = array[0];
            finishing = array[0];
        }
        else if(array.length==2) {
            starting = array[0];
            finishing = array[1];
        }
        start = LocalDate.parse(starting);
        end = LocalDate.parse(finishing);
        boolean flag = !start.isAfter(end);
        if (flag) {
            list = collectionDateAdd(true);
        } else {
            list = collectionDateReverseAdd(false);
        }
        return list;
    }
    private List<LocalDate> collectionDateAdd(boolean flag) {
        List<LocalDate> list = new LinkedList<>();
        while (flag) {
            list.add(start);
            start = start.plusDays(1);
            if(start.isAfter(end)) {
                flag = false;
            }
        }
        return list;
    }
    private List<LocalDate> collectionDateReverseAdd(boolean flag) {
        List<LocalDate> list = new LinkedList<>();
        while (!flag) {
            list.add(start);
            start = start.minusDays(1);
            if(start.isBefore(end)) {
                flag = true;
            }
        }
        return list;
    }
}
