package HomeWork7;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dates {
    private static String reformatStart = null;
    private static String reformatFinish = null;
    private static LocalDate start;
    private static LocalDate end;
    private static List<LocalDate> totalDates = new ArrayList<>();

    public static List<LocalDate> getTotalDates(String[] arrray) {
        totalDates = doWork(arrray);
        return totalDates;
    }

    private static SimpleDateFormat from = new SimpleDateFormat("dd-MM-yyyy");
    private static SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
    private static String starting;
    private static String finishing;

    public static List<LocalDate> doWork(String[] args) {
        List<LocalDate> list;
        if(args.length==1) {
            list = oneDate(args);
        }else {
            list = twoDate(args);
        }
        return list;
    }

    private static List<LocalDate> collectionDateAdd(boolean flag) {
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
    private static List<LocalDate> collectionDateReverseAdd(boolean flag) {
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

    private static List<LocalDate> oneDate(String[] array) {
        List<LocalDate> list = new LinkedList<>();
        starting = String.valueOf(array[0]).replaceAll("\\.","-");
        try {
            reformatStart = to.format(from.parse(starting));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        start = LocalDate.parse(reformatStart);
        list.add(start);
        return list;
    }
    private static List<LocalDate> twoDate(String[] array) {
        List<LocalDate> list;
        starting = String.valueOf(array[0]).replaceAll("\\.","-");
        finishing = String.valueOf(array[1]).replaceAll("\\.","-");
        try {
            reformatStart = to.format(from.parse(starting));
            reformatFinish = to.format(from.parse(finishing));
        }catch (ParseException e) {
            e.printStackTrace();
        }
        start = LocalDate.parse(reformatStart);
        end = LocalDate.parse(reformatFinish);
        boolean flag = !start.isAfter(end);
        if (flag) {
            list = collectionDateAdd(true);
        } else {
            list = collectionDateReverseAdd(false);
        }
        return list;
    }
}
