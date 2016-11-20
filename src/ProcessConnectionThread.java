import javax.microedition.io.StreamConnection;

public class ProcessConnectionThread extends Thread{
	
	private StreamConnection connection;
	
	public ProcessConnectionThread(StreamConnection connection) {
		this.connection = connection;
	}
	
	@Override
	public void run() {
		System.out.println("Processing connection...");
	}
}
