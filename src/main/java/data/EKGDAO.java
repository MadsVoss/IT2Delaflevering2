package data;

import java.util.LinkedList;
import java.util.List;

public interface EKGDAO {
    void saveEkg(LinkedList<EKGDTO> ekgdtobatch);
    List<EKGDTO> loadEKG(String id);
}
