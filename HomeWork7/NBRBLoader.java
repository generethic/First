package HomeWork7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Загрузчик курса с сайта Нац. Банка
 */
public class NBRBLoader extends SiteLoader{
    Map<Double,LocalDate> map = new LinkedHashMap<>();
    /**
     * Метод для запуска загрузки курса валют
     * @param currencyName валюта которую мы ищем
     * @return курс который мы нашли
     */
    @Override
    public double load(SiteLoader.Currency currencyName) {
        return load("https://www.nbrb.by/api/exrates/rates/" + currencyName.getId(), currencyName);
    }

    /**
     * Обработка результата загрузки с сайта банка
     * @param content то что получилось загрузить
     * @param currencyName валюта которую мы ищем
     * @return курс который мы нашли
     */
    @Override
    protected double handle(String content, SiteLoader.Currency currencyName) {
        //TODO дописываем код сюда
        int endOfString = content.indexOf("}");
        double result = Double.parseDouble(String.valueOf(content.subSequence(endOfString-6,endOfString)));
        return result;
    }
    protected void handleToMap(String content, SiteLoader.Currency currencyName) {
        //TODO дописываем код сюда
        int endOfString = content.indexOf("}");
        double doubleResult = Double.parseDouble(String.valueOf(content.subSequence(endOfString-6,endOfString)));
        LocalDate localDateResult = findDate(content);
        map.put(doubleResult,localDateResult);
        for(Map.Entry<Double,LocalDate> entry : map.entrySet()) {
            System.out.println(entry.getValue() + " | " + entry.getKey());
        }
    }
    protected String getHTML(String urlToRead) throws Exception {
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
    protected String urlToString(String URL) {
        String line = null;
        try {
            line = getHTML(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }
    public void loadDates(SiteLoader.Currency currencyName,String[] array) {
        System.out.println("CurrencyName is "+currencyName+":");
        String site = null;
        List<LocalDate> list = Dates.getTotalDates(array);
        for (LocalDate localDate : list) {
            try {
                String toSite = "https://www.nbrb.by/api/exrates/rates/" + currencyName.getId() + "?"+"ondate=" + localDate;
                site = urlToString(toSite);
                map.put(handle(site,currencyName),localDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(Map.Entry<Double,LocalDate> entry : map.entrySet()) {
            System.out.println(entry.getValue() + " | " + entry.getKey());
        }
    }
    public LocalDate findDate(String content) {
        String reformatStart = null;
        SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
        String line = "{\"Cur_ID\":292,\"Date\":\"2020-11-26T00:00:00\",\"Cur_Abbreviation\":\"EUR\",\"Cur_Scale\":1,\"Cur_Name\":\"Евро\",\"Cur_OfficialRate\":3.0438}";
        System.out.println(line);
        String word = line.substring(line.indexOf("Date"),line.indexOf(",",line.indexOf("Date"))).replace(":","!");
        String newNew = word.substring(word.indexOf("!")+1).replaceAll("\"","").replaceAll("!",":");
        try {
            reformatStart = to.format(from.parse(newNew));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return LocalDate.parse(reformatStart);
    }
}
