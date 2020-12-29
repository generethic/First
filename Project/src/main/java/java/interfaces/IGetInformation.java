package interfaces;

import java.time.LocalDate;
import java.util.LinkedHashMap;
/**
 * Иитерфейс, который необходимо реализовать классу, чтобы иметь возможность получить информацию с сайта, либо
 * иного источника согласно передаваемых в параметрах дат
 */
public interface IGetInformation {
    LinkedHashMap<String,Double> getInformation(LocalDate...dates);
}
