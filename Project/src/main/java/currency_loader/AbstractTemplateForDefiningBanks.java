package currency_loader;

import interfaces.IGetInformation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
/**
 * Класс в котором определена логика подключению к сайтус информацией,а также
 * определены переменные и методы для классов-наследников
 */
public abstract class AbstractTemplateForDefiningBanks implements IGetInformation {
    /** Свойство - flag. Определяет как необходимо будет формировать даты
     * (каждую конкретно, или диапазон от ранней к поздней дате */
    private boolean flag;
    /** Свойство - line. Свойство сохраняем в себе информацию, полученную
     * в результате перехода по ссылке */
    //line
    private String line;
    /** Получает значение свойства flag
     @return Значение свойства flag
     */
    protected boolean isFlag() {
        return flag;
    }
    /** Задает значение свойства flag, которое можно получить при помощи метода {@link #isFlag()}
     @param flag Новое значение свойства flag
     */
    protected AbstractTemplateForDefiningBanks(boolean flag) {
        this.flag = flag;
    }
    /** Абстрактный метод, который делит полученную строку,полученную в параметрах по делиметру, который определён
     * в классе-наследнике
     @return массив строк
     */
    public abstract String[] split(String word);
    /** Метод, который отвечает за подключение к сайту
     @return массив строк, полученных в результате получения информации с сайта
     */
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
    /** Абстрактный метод, который отвечает за преобразование дат по шаблонам, которые определены
     * в классе-наследнике
     @return строка, содержащая дату
     */
    protected abstract String reworkDate(LocalDate date);
}
