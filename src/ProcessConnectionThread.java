 import com.pi4j.io.gpio.*;

import javax.microedition.io.StreamConnection;
import java.io.InputStream;

public class ProcessConnectionThread extends Thread{

	private StreamConnection connection;
	private final GpioPinDigitalOutput pin;

	public ProcessConnectionThread(StreamConnection connection) {
		this.connection = connection;

		GpioController gpio = GpioFactory.getInstance();
		pin = gpio.provisionDigitalOutputPin(
			RaspiPin.GPIO_01, "LED", PinState.HIGH);
		pin.setShutdownOptions(true, PinState.LOW);
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
			int ledStatus = -1;
			try {
				ledStatus = inputStream.read();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.println("Led status: " + ledStatus);
			if(ledStatus == 1) {
				System.out.println("ON");
				pin.high();
			} else {
				System.out.println("OFF");
				pin.low();
				//turn led off
			}
		}
	}
}
