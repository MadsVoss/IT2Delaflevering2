import jssc.*;

public class Sensor {
    //Opretter SerialPort så vi senere kan gå ind og få fat i det hvor pulsmåleren sidder. Derefter opretter vi en string som vi kan sætte et resultat ind i.
    private SerialPort serialPort = null;
    private String result = null;


    //Denne finder ud af hvor sensoren er plugget til og "snakker" med den
    public Sensor(int portnummer) {

        String[] portNames = SerialPortList.getPortNames();
        try {
            serialPort = new SerialPort(portNames[portnummer]);
            serialPort.openPort();
            serialPort.setParams(115200, 8, 1, 0);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            serialPort.setDTR(true);
            serialPort.setRTS(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Her går vi ind og finder selve dataen fra pulsmåleren så vi kan bruge det inde i Monitor klassen.
    public String getData() {
        try {
            //For den her til at "sove" et stykke tid så vi sørger for den snakker ordentligt sammen med vores Arduino kode
            Thread.sleep(20);
            //Her siger vi at vi kun vil have resultatet hvis den string fra Arduino er 14 tegn eller længere. Så vi sikrer at vi ikke får en masse fejlmålinger som 0 taller osv med ind.
            if (serialPort.getInputBufferBytesCount() >= 14) {
                result = serialPort.readString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}