package interfaces;

import java.time.LocalDate;
import java.util.LinkedHashMap;

public interface IGetInformation {
    LinkedHashMap<String,Double> getInformation(IGetInformation information, LocalDate...dates);
}
