package data;

import java.util.List;

public interface TemperatureDAO {
    void saveTemp(TempMeasure tempMeasure);
    List<TempMeasure> loadTemp(String id);
}