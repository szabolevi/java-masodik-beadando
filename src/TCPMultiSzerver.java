

// TCPMultiServer.java
import java.net.*;
import java.io.*;

public class TCPMultiSzerver {
    public static void main(String args[]) {
	
	int port;
	ServerSocket server_socket;
	
	try { 
	    port = Integer.parseInt(args[0]);
	}
	catch (Exception e) {
	    port = 1500;
	}

	try {
	    server_socket = new ServerSocket(port);
	    System.out.println("Szerver varakozik a kliensre a(z) " + server_socket.getLocalPort() + "-as porton");
	    
	    while(true) {
		new TCPMultiSzerverThread(server_socket.accept()).start();		
	    }
	}
	catch (IOException e) {
	    System.out.println(e);
	}
    }
}
