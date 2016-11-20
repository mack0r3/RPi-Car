import javax.microedition.io.StreamConnection;
import java.io.InputStream;

public class ProcessConnectionThread extends Thread{
	
	private StreamConnection connection;
	
	public ProcessConnectionThread(StreamConnection connection) {
		this.connection = connection;
	}
	
	@Override
	public void run() {
		InputStream inputStream = connection.openInputStream();
		System.out.println("Waiting for inputStream");
		while(true) {
			int ledStatus = inputStream.read();
			System.out.println("Led status: " + ledStatus);
			if(ledStatus == 1) {
				//turn led on
			} else {
				//turn led off
			}
		}
	}
}
