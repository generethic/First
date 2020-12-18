package Currency_Parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.*;
/*
Класс в котором прописана логика работы с датами, подключение к сайтам банков для получения информации
 */
public abstract class ParseUrlToResult {
    protected String newUrl;
    private BufferedReader reader;
    private String line;
    protected String dayFirst;
    protected String dayLast;
    protected LinkedHashMap<String, Double> map;
    protected List<LocalDate> list;
    protected String[] datesArray;
    protected Pattern pattern = Pattern.compile("\\d+\\.\\d*\\.\\d+");
    protected Pattern pattern1 = Pattern.compile("\\d+\\,\\d+");
    protected Matcher matcher;
    protected Matcher matcher1;
    private HttpURLConnection conn;

    private void getInfoFromUrl() {
        try {
            URL url = new URL(newUrl);
            HttpURLConnection.setFollowRedirects(false);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected String[] split(String word) {
        return word.split("<Record Date=");
    }
    protected String[] getCurrencyArray(String URL) {
        getInfoFromUrl();
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String word = line;
        try {
            reader.close();
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return split(word);
    }

}
