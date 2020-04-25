package data;

import java.sql.Timestamp;

public class TempMeasure {
    Double measurement;
    Timestamp time;

    public Double getMeasurement() {
        return measurement;
    }

    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
