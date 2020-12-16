import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CBR {
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
            date1 = new CbrParseDate().newDateFormat.format(new CbrParseDate().oldDateFormat.parse(list.get(0).toString()));
            date2 = new CbrParseDate().newDateFormat.format(new CbrParseDate().oldDateFormat.parse(list.get(list.size()-1).toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newUrl = "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1="+date1+"&date_req2="+date2+"&VAL_NM_RQ="+basic;
        newUrl = newUrl.replaceAll("-","/");
        map = getCurrency(newUrl);
        return map;
    }
    private LinkedHashMap<String,Double> getCurrency(String URL) {
        LinkedHashMap<String,Double> newMap = new LinkedHashMap<>();
        Pattern pattern = Pattern.compile("\\d+\\.\\d*\\.\\d+");
        Pattern pattern1 = Pattern.compile("\\d+\\,\\d+");
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
                String value = matcher1.group().replace(",",".");
                newMap.putIfAbsent(date, Double.valueOf(value));
            }
        }
        return newMap;
    }
    private String[] split(String word) {
        String[] array = word.split("<Record Date=");
        return array;
    }
}
