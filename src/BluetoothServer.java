public class BluetoothServer {
	public static void main(String[] args) {				
		Thread thread = new Thread(new AcceptConnectionThread());
		thread.start();
	}
}
