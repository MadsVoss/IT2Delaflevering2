package data;

import java.util.List;

public interface EKGListener {
    void notifyEKG(List<Integer>ekgData);
}
