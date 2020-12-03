package HomeWork7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Загрузчик курса с сайта Альфа-Банка
 */
public class AlfaLoader extends SiteLoader {
    private Map<String, Double> map;
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

    private static List<String> toCollection(String line) {
        line = line.substring(line.indexOf("["), line.length() - 1);
        String[] array = line.split("},\\{");
        List<String> list = new LinkedList<>(Arrays.asList(array));
        return list;
    }

    private static LinkedList<String> deleteFromList(List<String> list) {
        LinkedList<String> newList = new LinkedList<>();
        Pattern pattern = Pattern.compile("(\"[A-Za-z]+\":\"[A-Z]+\")");
        Matcher matcher;
        for (int i = 0; i < list.size(); i++) {
            matcher = pattern.matcher(list.get(i));
            while (matcher.find()) {
                if (matcher.group().contains("\"buyCode\"")) {
                    if (matcher.group().equals("\"buyIso\":BYN"))
                        newList.add(list.get(i));
                }
            }
        }
        return newList;
    }

    private double currency(String currency) {
        double result = 0;
        Pattern pattern = Pattern.compile("\"[A-Za-z]+\":\\d+");
        Matcher matcher;
        for (int i = 0; i < list.size(); i++) {
            matcher = pattern.matcher(list.get(i));
            while (matcher.find()) {
                if (matcher.group().contains("\"sellCode\":" + currency)) {
                    String newLine = list.get(i);
                    pattern = Pattern.compile("(\"[A-Za-z]+\":\\d+\\.\\d+)");
                    matcher = pattern.matcher(newLine);
                    while (matcher.find()) {
                        if (matcher.group().contains("\"buyRate\":")) {
                            newLine = matcher.group();
                            result = Double.parseDouble(newLine.substring(newLine.indexOf(":") + 1));
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public double load(Currency currencyName) {
        map = new LinkedHashMap<>();
        LocalDate today = LocalDate.now();
        List<LocalDate> newlist = Collections.singletonList(today);
        for (LocalDate localDate : newlist) {
            try {
                map.put(localDate + " | " + currencyName, handle(null, currencyName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            System.out.println("Date " + entry.getKey() + " | " + entry.getValue());
        }

        return 0;
    }

    @Override
    protected double handle(String content, Currency currencyName) {
        double result;
        line = urlToString();
        list = toCollection(line);
        String curr = convertISOtoNBRB(currencyName);
        result = currency(curr);
        return result;
    }

    private static String convertISOtoNBRB(Currency currency) {
        String line = null;
        switch (currency.getId()) {
            case "145": {
                line = CurrencyСodes.getUSDCode();
                break;
            }
            case "292": {
                line = CurrencyСodes.getEURCode();
                break;
            }
            case "298": {
                line = CurrencyСodes.getRUBCode();
                break;
            }
        }
        return line;
    }

    public void toFile(SiteLoader.Currency[] currencies) {
        for (int i = 0; i < currencies.length; i++) {
            LocalDate today = LocalDate.now();
            List<LocalDate> newlist = Collections.singletonList(today);
            for (LocalDate localDate : newlist) {
                try {
                    ToFile.resultMap.put(localDate + " | " + currencies[i], handle(null, currencies[i]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}