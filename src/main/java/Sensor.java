/** @author {Mads Voss, Mikkel Bech, Dalia Pireh, Sali Azou, Beant Sandhu}*/
import jssc.*;

public class Sensor {
    private SerialPort serialPort = null;
    private String result = null;
    private int value = 0;

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
    public int getData() {
        try {
            Thread.sleep(20);
            if (serialPort.getInputBufferBytesCount() > 0) {
                result = serialPort.readString();
                if(result != "") {
                    value = Integer.parseInt(result.substring(result.indexOf(""), result.indexOf("#")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void main(String[] args) throws InterruptedException {
        Sensor sensor = new Sensor(1);
        while(true){
            Thread.sleep(10);
            int data = sensor.getData();
            System.out.println(data);
        }
    }
}