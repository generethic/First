package HomeWork7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Загрузчик курса с сайта Альфа-Банка
 */
public class AlfaLoader{
    private String line = null;

    private List<String> list = null;

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

    private String urlToString() {
        try {
            line = getHTML("https://developerhub.alfabank.by:8273/partner/1.0.0/public/rates");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    public double load(String word) {
        double result;
        line = urlToString();
        list = toCollection(line);
        deleteFromList(list);
        result = currency(word);
        return result;
    }

    private static List<String> toCollection(String line) {
        line = line.substring(line.indexOf("["),line.length()-1);
        String[] array = line.split("},\\{");
        List<String> list = new LinkedList<>(Arrays.asList(array));
        return list;
    }

    private static void deleteFromList(List<String> list) {
        Pattern pattern = Pattern.compile("(\"[A-Za-z]+\":\"[A-Z]+\")");
        Matcher matcher;
        int position = 0;
        for (int i = 0; i< list.size();i++) {
            matcher = pattern.matcher(list.get(i));
            while (matcher.find()) {
                if(matcher.group().contains("\"buyCode\":933")) {
                    if(!matcher.group().equals("\"buyCode\":933"))
                        position = i;
                }
            }
            list.remove(position);
        }
    }
    private double currency(String currency) {
        double result = 0;
        Pattern pattern = Pattern.compile("\"[A-Za-z]+\":\\d+");
        Matcher matcher;
        for (int i = 0; i< list.size();i++) {
            matcher = pattern.matcher(list.get(i));
            while (matcher.find()) {
                if(matcher.group().contains("\"sellCode\":"+currency)) {
                    String newLine = list.get(i);
                    pattern = Pattern.compile("(\"[A-Za-z]+\":\\d+\\.\\d+)");
                    matcher = pattern.matcher(newLine);
                    while (matcher.find()) {
                        if(matcher.group().contains("\"buyRate\":")) {
                            newLine = matcher.group();
                            result = Double.parseDouble(newLine.substring(newLine.indexOf(":")+1));
                        }
                    }
                }
            }
        }
    return result;
    }

}
