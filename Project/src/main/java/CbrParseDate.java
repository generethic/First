import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

class CbrParseDate extends ParseDate {

    List<LocalDate> getTotalDates(String[] array) {
        return twoDate(array);
    }
    @Override
    List<LocalDate> twoDate(String[] array) {
        list = new LinkedList<>();
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
}
