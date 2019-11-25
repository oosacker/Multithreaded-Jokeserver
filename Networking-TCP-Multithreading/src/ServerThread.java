import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerThread extends Thread{
	
	private Socket socket;
	private static ArrayList<String> jokesArray;
	
	private String randomJoke;
	
	public ServerThread(Socket socket, String randomJoke) {
		this.socket = socket;
		this.randomJoke = randomJoke;
	}
	

    public void run(){
    	try {
    		//readJokes();
    		
    		InputStream input = socket.getInputStream();
    		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    		OutputStream output = socket.getOutputStream();
    		PrintWriter writer = new PrintWriter(output, true);
    		
    		int i = 0;
    		String text = reader.readLine();
    		
    		//long start = System.nanoTime();
    		
    		do {
    			//int random = (int)(Math.random() * jokesArray.size());
    			//String myJoke = jokesArray.get(random);
    			//writer.println(myJoke);
    			
    			writer.println(randomJoke);
    			
    			System.out.println("Sent joke");
    			//ystem.out.println(i + " " +text);
    			//System.out.println("Received: "+text);
    			
    			System.out.println("Client port: " +socket.getPort());
    			System.out.println("Client IP: " +socket.getInetAddress().getHostAddress());
    			
    			text = reader.readLine();
    			i++;
    			
    		}while( !text.equals("stop") );
    		
    		System.out.println("Server End"); 
    		//long time = System.nanoTime() - start;
    		System.out.println("~~~~~~~~~~~~~~~~~");
    		//System.out.println("Time (ns): " +time);
    		
    		
    		input.close();
    		output.close();
    		socket.close();
    		
    	}
    	
    	catch(Exception e){
    		e.printStackTrace();
    		
    	}
		
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
