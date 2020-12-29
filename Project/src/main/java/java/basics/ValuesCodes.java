package basics;
/**
 * Enum,включающий в себя коды валют
 */
public enum ValuesCodes {
    /** Перечисления - коды валют для нац. банка РФ и РБ */
    EUR("R01239","292"),
    USD("R01235","145"),
    RUB("","298");

    /** Свойство - имя валюты */
    private String nameCBR;
    /** Свойство - имя валюты */
    private String nameNBRB;
    /** Создает новый объект
     @param nameCBR Имя валюты нац. банка РФ
     @param nameNBRB Имя валюты нац. банка РБ
     */
    ValuesCodes(String nameCBR, String nameNBRB){
        this.nameCBR = nameCBR;
        this.nameNBRB = nameNBRB;
    }
    /** Получает значение свойства nameCBR
     @return Значение свойства nameCBR
     */
    public String getNameCBR() {
        return nameCBR;
    }
    /** Получает значение свойства nameNBRB
     @return Значение свойства nameNBRB
     */
    public String getNameNBRB() {
        return nameNBRB;
    }


}
