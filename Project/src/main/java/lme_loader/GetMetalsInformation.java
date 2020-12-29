package lme_loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Аюстрактный класс, в котором реализовано получение информации с сайта Westmetals.com и экспорт её в String формат. Класс
 * который является его наследником в свою очередь, продолжает работу с данными, получеными тут.
 */
abstract class GetMetalsInformation {
    /** Метод, который отвечает за получение информации с сайта по ссылке в виде строки
     @return Строка с информацией с сайта
     */
    //
    protected String handleFromUrl(String link) {
        StringBuilder result = null;
        String word = "";
        URL url;
        HttpURLConnection conn = null;
        try {
            url = new URL(link);
            HttpURLConnection.setFollowRedirects(false);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert conn != null;
        try (InputStream stream = conn.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                while (true) {
                    try {
                        if ((word = reader.readLine()) == null) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert false;
                    result = (result == null ? new StringBuilder("null") : result).append(word);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        assert false;
        conn.disconnect();
        return result.toString();
    }
}
