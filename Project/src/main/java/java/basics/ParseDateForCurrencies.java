package basics;
/**
 * Enum,включающий в себя шаблоны для форматирования дат
 */

public enum ParseDateForCurrencies {
    /** Перечисления - допустимые шаблоны */
    DATE_PATTERN_1("dd.MM.yyyy"),
    DATE_PATTERN_2("yyyy-MM-dd"),
    DATE_PATTERN_5("yyyy-M-d"),
    DATE_PATTERN_3("dd MMMM yyyy"),
    DATE_PATTERN_4("dd/MM/yyyy");
    /** Свойство - имя */
    private String name;
    /** Создает новый объект
     @param name Имя валюты
     */
    ParseDateForCurrencies(String name){
        this.name = name;
    }
    /** Получает значение свойства name
     @return Значение свойства name
     */
    public String getName() {
        return name;
    }
}
