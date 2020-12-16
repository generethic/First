import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public abstract class ParseDate {
    protected SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    protected SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    LocalDate start;
    LocalDate end;
    String starting;
    String finishing;
    List<LocalDate> list;

    List<LocalDate> oneDate(String[] array) {
        return null;
    }

    List<LocalDate> twoDate(String[] array) {
        return null;
    }

    public List<LocalDate> doWork(String[] args) {
        if (args.length == 1) {
            list = oneDate(args);
        } else {
            list = twoDate(args);
        }
        return list;
    }

    List<LocalDate> collectionDateAdd(boolean flag) {
        while (flag) {
            if (start.isAfter(end)) {
                break;
            }
            list.add(start);
            start = start.plusDays(1);
        }
        return list;
    }

    List<LocalDate> collectionDateReverseAdd(boolean flag) {
        while (!flag) {
            if(end.isAfter(start)) {
                break;
            }
            list.add(end);
            end = end.plusDays(1);
        }
        return list;
    }
}
