package basics;
/* enum c указанием значний, которые будут использоваться в классе WestMetals для получения итоговый ссылки с таблицей результатов
для металлов: медь(Copper) и алюминий (Aluminum)
   */

public enum WestMetalsValues {
    /** Перечисления - параметры для металлов, о которых необходимо получить информацию */
    Aluminum("LME_Al_cash"),
    Copper("LME_Cu_cash");
    /** Свойство - наименование металла */
    private String name;
    /** Создает новый объект
     @param name наименование металла
     */
    WestMetalsValues(String name){
        this.name = name;
    }
    /** Получает значение свойства nameCBR
     @return Значение свойства наименования металла
     */
    public String getName() {
        return name;
    }
}
