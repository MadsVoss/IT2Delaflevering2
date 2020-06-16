/** @author {Mads Voss, Mikkel Bech, Dalia Pireh, Sali Azou, Beant Sandhu}*/
package data;

import java.util.List;

public interface TemperatureDAO {
    void saveTemp(TempMeasure tempMeasure);
    List<TempMeasure> loadTemp(String id);
}