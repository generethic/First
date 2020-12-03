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
public class NBRBLoader extends SiteLoader{
    Map<String,Double> map;
    @Override
    public double load(SiteLoader.Currency currencyName) {
        return 0;
    }

    @Override
    protected double handle(String content, Currency currencyName) {
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
    private String line = null;
    private String urlToString(String URL) {
        String line = null;
        try {
            line = getHTML(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }
    public Map<String,Double> loadDates(SiteLoader.Currency currencyName,String[] array) {
        ToFile test = new ToFile();
        test.resultMap = new HashMap<>();
        map = new LinkedHashMap<>();
        String site;
        List<LocalDate> list = Dates.getTotalDates(array);
        for (LocalDate localDate : list) {
            try {
                String toSite = "https://www.nbrb.by/api/exrates/rates/" + currencyName.getId() + "?"+"ondate=" + localDate;
                site = urlToString(toSite);
                map.put(localDate +" | "+currencyName,handle(site,currencyName));
                test.resultMap.put(localDate +" | "+currencyName,handle(site,currencyName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(Map.Entry<String,Double> entry : map.entrySet()) {
            System.out.println("Date "+entry.getKey() + " | " + entry.getValue());
        }

        return map;
    }

    public Map<String,Double> loadDates(SiteLoader.Currency currencyName) {
        ToFile test = new ToFile();
        test.resultMap = new HashMap<>();
        map = new LinkedHashMap<>();
        String site;
        LocalDate today = LocalDate.now();
        List<LocalDate> list = Collections.singletonList(today);
        for (LocalDate localDate : list) {
            try {
                String toSite = "https://www.nbrb.by/api/exrates/rates/" + currencyName.getId() + "?"+"ondate=" + localDate;
                site = urlToString(toSite);
                map.put(localDate +" | "+currencyName,handle(site,currencyName));
                test.resultMap.put(localDate +" | "+currencyName,handle(site,currencyName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(Map.Entry<String,Double> entry : map.entrySet()) {
            System.out.println("Date "+entry.getKey() + " | " + entry.getValue());
        }
        return map;
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
}
