import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

public class Client {

	public Client() throws Exception {
		

			//InetAddress ip = InetAddress.getLocalHost();  
			int serverPort = 3000;	// server port number





			Socket socket = new Socket(InetAddress.getLocalHost(), serverPort);

			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);

			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			writer.println("start");
			String joke = reader.readLine();
			System.out.println("Received: "+joke);
			

			
			
			writer.println("stop");
			socket.close();


		
		
	}


	public static void main(String[] args) throws Exception {
		int i = 0;
		long start = System.nanoTime();
		System.out.println("~~~~~~~~~~~~~~~~~");
		System.out.println("Client #" +i+ " running..."); 

		do {
			new Client();
			System.out.println("Client #" +i+ " End"); 
			i++;
		}while (i<100);

		long time = System.nanoTime() - start;
		System.out.println("~~~~~~~~~~~~~~~~~");
		System.out.println("Time (ns): " +time);

	}

}
