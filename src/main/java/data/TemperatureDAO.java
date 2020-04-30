package data;

import java.util.List;

public interface TemperatureDAO {
    void save(TempMeasure tempMeasure);
    List<TempMeasure> load(String id);
}

