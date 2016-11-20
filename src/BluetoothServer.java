import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class BluetoothServer {
	public static void main(String[] args) {				
		Thread thread = new Thread(new AcceptConnectionThread());
		thread.start();
	}
}
