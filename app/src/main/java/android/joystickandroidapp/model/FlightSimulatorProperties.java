package android.joystickandroidapp.model;

public class FlightSimulatorProperties {
    private String ip;
    private String port;

    public FlightSimulatorProperties(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public FlightSimulatorProperties() {
        this.ip = "";
        this.port = "";
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
