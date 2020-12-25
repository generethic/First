package lme_loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

abstract class GetMetalsInformation {
    //метод, который отвечает за получение информации с сайта по ссылке в виде строки
    protected String handleFromUrl(String link) {
        StringBuilder result = null;
        String word = "";
        URL url;
        URLConnection con = null;
        try {
            url = new URL(link);
            con = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (con != null) {
            try(BufferedReader reader  = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
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
        }
        assert false;
        return result.toString();
    }
}
