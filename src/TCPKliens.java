

// TCPKliens.java
import java.net.*;
import java.io.*;

public class TCPKliens {    
    public static void main(String[] args) {
	int port = 1500;
	String server = "localhost";
	Socket socket = null;
	String lineToBeSent;
	BufferedReader input;
	PrintWriter output;
	
	if(args.length == 2) {
	    server = args[0];
	    try { 
		port = Integer.parseInt(args[1]);
	    }
	    catch (Exception e) {
		System.out.println("szerver port = 1500 (default)");
		port = 1500;
	    }
	}
	//Szerverhez kapcsolodas
	try {
	    socket = new Socket(server, port);
	    System.out.println("Kapcsolat letrehozva a szerverrel: " +
				   socket.getInetAddress() +
				   ":" + socket.getPort());
	}
	catch (UnknownHostException e) {
	    System.out.println(e);
	}
	catch (IOException e) {
	    System.out.println(e);
	}
/**
 * Szolgaltatas kezdete
 */
	
	try {
	    input = new BufferedReader(new InputStreamReader(System.in)); 
	    output = new PrintWriter(socket.getOutputStream(),true);
	    System.out.println("Irja be az elkuldeni kivant uzenetet:");
	    
	    // beolvassuk az uzenetet, tovabbitjuk a szervernek
	    while(true) {
		lineToBeSent = input.readLine();
		// "." jelre kapcsolat zarasa
		if(lineToBeSent.equals(".")) break;
		output.println(lineToBeSent);
	    }
	}
	catch (IOException e) {
	    System.out.println(e);
	}
	try {
	    socket.close();
	}
	catch (IOException e) {
	    System.out.println(e);
	}
    }
/**
 * Szolgaltatas vege 
 */
}
 