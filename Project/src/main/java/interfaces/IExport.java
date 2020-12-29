package interfaces;

import java.util.LinkedHashMap;
import java.util.List;
/**
 * Иитерфейс, который необходимо реализовать классу, чтобы иметь возможность экспортировать данные в файл.
 * Параметр list отвечает за список данных, которе необходимо экспортировать
 * Параметр destination - расположения файл с экспортированными данными
 */
public interface IExport {
    void export(List<LinkedHashMap<String,Double>> list, String destination);
}
