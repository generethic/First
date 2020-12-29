package basics;
/**
 * Enum,включающий в себя шаблоны в которых будет проводиться поиск информации, полученной из html-документа
 */

public enum PatternsForParseWestMetals {
    /** Перечисления - допустимые шаблоны */
    PATTERN1("tr"),
    PATTERN2("td");
    /** Свойство - имя */
    private String name;
    /** Создает новый объект
     @param name Имя валюты
     */
    PatternsForParseWestMetals(String name){
        this.name = name;
    }
    /** Получает значение свойства name
     @return Значение свойства name
     */
    public String getName() {
        return name;
    }
}
