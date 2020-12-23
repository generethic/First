package currency_loader;

import interfaces.IGetInformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
/*
Класс в котором прописана логика работы с датами, подключение к сайтам банков для получения информации
 */
public abstract class ParseUrlToResult implements IGetInformation {
    private boolean flag;
    private String basic;
    private String newUrl;
    private BufferedReader reader;
    private String line;
    private HttpURLConnection conn;

    public boolean isFlag() {
        return flag;
    }

    public String getBasic() {
        return basic;
    }

    public ParseUrlToResult(boolean flag, String basic) {
        this.flag = flag;
        this.basic = basic;
    }

    public String getNewUrl() {
        return newUrl;
    }

    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }



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
    abstract String[] split(String word);
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
    abstract String reworkDate(LocalDate date);
}
