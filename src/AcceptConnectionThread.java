import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

import java.util.*;



public class AcceptConnectionThread extends Thread {

    private static final String serviceName = "RpiService";
    private static final String uuid = "1ea6b38800001000800000805f9b34fb";

    public AcceptConnectionThread() {

    }

    @Override
    public void run() {
        acceptConnection();
    }

    private void acceptConnection() {
        StreamConnectionNotifier notifier = null;
        StreamConnection connection = null;
        try {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            localDevice.setDiscoverable(DiscoveryAgent.GIAC);
            String connectionURL = createConnectionURL();
            notifier = (StreamConnectionNotifier)Connector.open(connectionURL);
        } catch (Exception ex) {
            Log("Error opening bluetooth connection");
            Log(createConnectionURL());
            ex.printStackTrace();
            return;
        }

        while(true) {
            try {
                Log("Waiting for bluetooth connection...");
                connection = notifier.acceptAndOpen();
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }

            if (connection != null) {
                Log("Connection established...");
                Thread thread = new Thread(new ProcessConnectionThread(connection));
                thread.start();
                try {
                    notifier.close();
                } catch (Exception ex) {
                    Log("Error closing bluetooth stream");
                    ex.printStackTrace();
                }

                return;
            }
        }

    }

    private String createConnectionURL() {
        return "btspp://localhost:" + uuid + ";name=" + serviceName;
    }

    private static void Log(String message) {
        System.out.println(message);
    }
}
