package basics;
/* enum c указанием кодов валют, где под переменными CBR типа String указаны коды нац. банка РФ, а под
    переменными NBRB типа String указаны коды нац. банка РБ
   */
public enum ValuesCodes {
    EUR("R01239","292"),
    USD("R01235","145"),
    RUB("","298");

    public String getNameCBR() {
        return nameCBR;
    }

    public String getNameNBRB() {
        return nameNBRB;
    }

    private String nameCBR;
    private String nameNBRB;
    ValuesCodes(String nameCBR, String nameNBRB){
        this.nameCBR = nameCBR;
        this.nameNBRB = nameNBRB;
    }

}
