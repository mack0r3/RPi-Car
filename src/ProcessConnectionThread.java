import java.nio.charset.StandardCharsets;
import javax.microedition.io.StreamConnection;
import java.io.InputStream;

public class ProcessConnectionThread extends Thread{

    private StreamConnection connection;
    private MotorRotator motorRotator;

    public ProcessConnectionThread(StreamConnection connection) {
        this.connection = connection;
        this.motorRotator = new MotorRotator();

    }

    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = connection.openInputStream();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Waiting for inputStream");
        while(true) {
            byte[] bytes = new byte[10];

            try {
                inputStream.read(bytes);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            String data = new String(bytes, StandardCharsets.UTF_8);
            MotorCoordinate motorCoordinate = DataParser.ParseMotorCoordinate(data);
            motorRotator.rotate(motorCoordinate);
        }
    }
}
