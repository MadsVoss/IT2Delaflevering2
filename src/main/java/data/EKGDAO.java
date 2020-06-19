package data;

import java.util.List;

public interface EKGDAO {
    void saveEkg(EKGMeasure ekgMeasure);
    List<EKGMeasure> loadEKG(String id);
}
