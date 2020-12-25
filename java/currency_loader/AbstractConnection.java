package currency_loader;

import interfaces.IGetInformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
/*
    Класс в котором определено подключение к сайту по переданной ссылке и определние абстрактных методов
 */
abstract class AbstractConnection implements IGetInformation {
    //flag определяет как необходимо будет формировать даты (каждую конкретно, или диапазон от ранней к поздней дате
    private boolean flag;
    //line результат того, что было полученно по ссылке
    private String line;
    //метод, дающий получить значение переменной flag
    public boolean isFlag() {
        return flag;
    }

    public AbstractConnection(boolean flag) {
        this.flag = flag;
    }
    //абстрактный метод, который делит полученную строку
    abstract String[] split(String word);
    //метод, который получает массив с данными, после перехода на ссылку и получения с неё информации
    protected String[] getCurrencyArray(String URL) {
        BufferedReader reader;
        HttpURLConnection conn;
        try {
            URL url = new URL(URL);
            HttpURLConnection.setFollowRedirects(false);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            line = reader.readLine();
            reader.close();
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return split(line);
    }
    //абстрактный метод, отвечающий за преобразование дат
    abstract String reworkDate(LocalDate date);
}
