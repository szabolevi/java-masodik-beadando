// TCPMultiSzerverThread.java
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.*;

public class TCPMultiSzerverThread extends Thread {
    private Socket socket = null;

    public TCPMultiSzerverThread(Socket socket) {
	super("TCPMultiSzerverThread");
	this.socket = socket;
    }

    public void run() {
    	
	System.out.println("Kapcsolat elfogadva: " + socket.getInetAddress() + ":" + socket.getPort());
	
	try {
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
		try {
		    while(true) {
				Date date = Calendar.getInstance().getTime();  
				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
				String strDate = dateFormat.format(date);  
				
				String message = input.readLine();
				if (message==null) break;
				System.out.println("[" + strDate + " " + socket.getInetAddress() + ":" + socket.getPort() + "] " + message);
				
				try
				{
				    String filename= "logs.txt";
				    FileWriter fw = new FileWriter(filename,true); 
				    fw.write("[" + strDate + " " + socket.getInetAddress() + ":" + socket.getPort() + "] " + message + "\n");
				    fw.close();
				}
				catch(IOException ioe)
				{
				    System.err.println("IOException: " + ioe.getMessage());
				}
		    }
		} catch (IOException e) {
		    System.out.println(e);
		}
		try {
		    socket.close();
		    System.out.println("Kapcsolat lezarasa");
		} catch (IOException e) {
		    System.out.println(e);
		}

	} catch (IOException e) {
	   	System.out.println(e);
	}
    }
}
