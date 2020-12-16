import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NBRB {
    private String newUrl;
    private URL url;
    private HttpURLConnection conn;
    private BufferedReader rd;
    private String line;
    private LinkedHashMap<String,Double> map;
    private LinkedHashMap<String,Double> newMap;
    private List<LocalDate> list;
    private String date1;
    private String date2;

    private void getInfoFromUrl() {
        try {
            url = new URL(newUrl);
            HttpURLConnection.setFollowRedirects(false);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    LinkedHashMap<String,Double> getInformation(String[] array, String basic) {
        map = new LinkedHashMap<>();
        list = new CbrParseDate().getTotalDates(array);
        try {
            date1 = new Dates().to.format(new Dates().from.parse(list.get(0).toString()));
            date2 = new Dates().to.format(new Dates().from.parse(list.get(list.size()-1).toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newUrl = "https://www.nbrb.by/API/ExRates/Rates/Dynamics/"+basic+"?startDate="+date1+"&endDate="+date2;
        map = getCurrency(newUrl);
        return map;
    }
    private LinkedHashMap<String,Double> getCurrency(String URL) {
        LinkedHashMap<String,Double> newMap = new LinkedHashMap<>();
        Pattern pattern = Pattern.compile("\\d+\\-\\d*\\-\\d+");
        Pattern pattern1 = Pattern.compile("\\d\\.\\d+");
        Matcher matcher;
        Matcher matcher1;
        getInfoFromUrl();
        try {
            line = rd.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String word = line;
        try {
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String array[] = split(word);
        for (String s : array) {
            matcher = pattern.matcher(s);
            matcher1 = pattern1.matcher(s);
            if(matcher.find() && matcher1.find()) {
                String date = matcher.group();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                String byPattern = "dd.MM.yyyy";
                DateFormat df = new SimpleDateFormat(byPattern);
                Date date1 = null;
                try {
                    date1 = formatter.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                date = df.format(date1);
                String value = matcher1.group().replace(",",".");
                newMap.putIfAbsent(date, Double.valueOf(value));
            }
        }
        return newMap;
    }
    private String[] split(String word) {
        String[] array = word.split("\\}");
        return array;
    }
}
