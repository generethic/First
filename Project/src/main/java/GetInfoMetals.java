import org.jsoup.Jsoup;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GetInfoMetals {
    private LinkedHashMap<String,Double> mapDates;
    private List<LocalDate> listLocalDates;
    private LinkedList<String> list = new LinkedList<>();
    private LinkedHashMap<String,Double> finalMap;

    LinkedHashMap<String,Double> getInformation(String URL,String[] array) {
        finalMap = new LinkedHashMap<>();
        mapDates = new LinkedHashMap<>();
        listLocalDates = new CbrParseDate().getTotalDates(array);
        for (LocalDate listLocalDate : listLocalDates) {
            String line = listLocalDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            list.add(line);
        }
        int count = 0;
        String date = null;
        Double value = null;
        org.jsoup.nodes.Document doc = null;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        org.jsoup.select.Elements rows = null;
        if (doc != null) {
            rows = doc.select("tr");
        }
        if (rows != null) {
            for(org.jsoup.nodes.Element row :rows) {
                org.jsoup.select.Elements columns = row.select("td");
                for (org.jsoup.nodes.Element column:columns) {
                    ++count;
                    if(count==1) {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                        String pattern = "dd.MM.yyyy";
                        DateFormat df = new SimpleDateFormat(pattern);
                        date = column.text().replace(".","");
                        Date date1 = null;
                        try {
                            date1 = formatter.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        date = df.format(date1);
                    }
                    if(count==2){
                        String result = column.text().replace(",","");
                        value = Double.parseDouble(result);
                    }
                    if(count==3) {
                        mapDates.putIfAbsent(date,value);
                        for(Map.Entry<String,Double> entry : mapDates.entrySet()) {
                            for (String s : list) {
                                if(entry.getKey().equals(s)) {
                                        finalMap.put(entry.getKey(),entry.getValue());
                                    }
                            }
                        }
                        count=0;
                        break;
                    }
                }
            }
        }
        return finalMap;
    }
}
