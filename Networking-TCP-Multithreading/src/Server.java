import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server{
	
	private static ArrayList<String> jokesArray;
	

	public static void main(String[] args) throws Exception {
		int port = 3000;
		
		ServerSocket serverSocket = new ServerSocket(port);
		
		
		
		System.out.println("Server running..."); 
		System.out.println("~~~~~~~~~~~~~~~~~");
		System.out.println("Server is listening on port " + port);
		
		
		readJokes();

		
		
		
		while(true){
			Socket socket = serverSocket.accept();
			System.out.println("New client connected");
			
			int random = (int)(Math.random() * jokesArray.size());
			String myJoke = jokesArray.get(random);
			
			//System.out.println("Client port: " +socket.getPort());
			//System.out.println("Client IP: " +socket.getInetAddress());
			//ServerThread myThread = new ServerThread(socket);
			
			(new ServerThread(socket, myJoke)).start();
			
			//myThread.start();	// each client launches a new thread
			
			//ServerThread myThread2 = new ServerThread(socket);
			//myThread2.start();
		}
		
		//serverSocket.close();

	}
	
    public static void readJokes() {
		jokesArray = new ArrayList<String>();
		String fileName = "jokes.txt";

		try {
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNextLine()) {
				String funnyJoke = scanner.nextLine();
				jokesArray.add(funnyJoke);
			}
			scanner.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
