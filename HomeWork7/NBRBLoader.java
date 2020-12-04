package HomeWork7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Загрузчик курса с сайта Нац. Банка
 */
class NBRBLoader extends SiteLoader{
    private Map<String,Double> map;
    private String site;
    private LocalDate today;

    private double handle(String content) {
        int endOfString = content.indexOf("}");
        return Double.parseDouble(String.valueOf(content.subSequence(endOfString-6,endOfString)));
    }

    private String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    private String urlToString(String URL) {
        String line = null;
        try {
            line = getHTML(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }
    void loadDates(String[] array, Currency[] currencyName) {
        initialize();
        List<LocalDate> list = Dates.getTotalDates(array);
        for (LocalDate localDate : list) {
            try {
                for (int i = 0; i < currencyName.length; i++) {
                    String toSite = "https://www.nbrb.by/api/exrates/rates/" + currencyName[i].getId() + "?" + "ondate=" + localDate;
                    site = urlToString(toSite);
                    map.put(localDate + " | " + currencyName[i], handle(site));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        printMap(map);
        ToFile.resultMap = map;
    }

    void loadDates(Currency[] currencyName) {
        initialize();
        List<LocalDate> list = Collections.singletonList(today);
        for (LocalDate localDate : list) {
            try {
                for (int i = 0; i < currencyName.length; i++) {
                    String toSite = "https://www.nbrb.by/api/exrates/rates/" + currencyName[i].getId() + "?" + "ondate=" + localDate;
                    site = urlToString(toSite);
                    map.put(localDate + " | " + currencyName[i], handle(site));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        printMap(map);
        ToFile.resultMap = map;
    }
    private LocalDate findDate(String content) {
        String reformatStart = null;
        SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
        String word = content.substring(content.indexOf("Date"),content.indexOf(",",content.indexOf("Date"))).replace(":","!");
        String newNew = word.substring(word.indexOf("!")+1).replaceAll("\"","").replaceAll("!",":");
        try {
            reformatStart = to.format(from.parse(newNew));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return LocalDate.parse(reformatStart);
    }
    private void printMap(Map<String,Double> newMap) {
        for(Map.Entry<String,Double> entry : newMap.entrySet()) {
            System.out.println("Date "+entry.getKey() + " | " + entry.getValue());
        }
    }
    private void initialize() {
        map = new LinkedHashMap<>();
        today = LocalDate.now();
    }
}
