package android.joystickandroidapp.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FlightSimulatorLogic {

    private FlightSimulatorProperties fgProperties;
    private Socket socket;
    private PrintWriter out;
    private ExecutorService executor;

    public FlightSimulatorLogic()
    {
        this.fgProperties = new FlightSimulatorProperties();
        this.executor = Executors.newFixedThreadPool(1);
        this.socket = new Socket();
    }

    public FlightSimulatorProperties getFgProperties() {
        return fgProperties;
    }

    public void setFgProperties(FlightSimulatorProperties fgProperties) {
        this.fgProperties = fgProperties;
    }

    public void connect ()  {

        executor.execute(() -> {

            try {
                socket.connect(new InetSocketAddress(fgProperties.getIp(), Integer.parseInt(fgProperties.getPort())), 2000);
                out = new PrintWriter(socket.getOutputStream(), true);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        });
    }

    public void setRudder(float value)
    {
        executor.execute(() -> {
                out.print("set /controls/flight/rudder " + value + "\r\n");
                out.flush();
        });
    }

    public void setThrottle(float value)
    {
        executor.execute(() -> {
            out.print("set /controls/engines/current-engine/throttle " +value+ "\r\n");
            out.flush();
        });
    }

    public void setElevator(float value)
    {
        executor.execute(() -> {
            out.print("set /controls/flight/elevator " + value + "\r\n");
            out.flush();
        });
    }

    public void setAileron(float value)
    {
        executor.execute(() -> {
            out.print("set /controls/flight/aileron " +value+ "\r\n");
            out.flush();
        });
    }
}
