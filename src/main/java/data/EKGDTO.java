package data;

import java.sql.Timestamp;

public class EKGDTO {
    private double ekg;
    private String cpr;
    private Timestamp timestamp;


    public double getEkg() {
        return ekg;
    }

    public void setEkg(double ekg) {
        this.ekg = ekg;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
