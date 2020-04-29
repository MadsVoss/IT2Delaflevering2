package data;

import java.util.List;

public interface TemperaturDAO {
    void save(TempMeasure tempMeasure);
    List<TempMeasure> load(String id);
}

