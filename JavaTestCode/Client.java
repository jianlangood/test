import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread{
	
	private static final String SERVERIP = "192.168.1.100";
	private static final int SERVERPORT = 54321;
	static BufferedReader bufferedReader;
	static PrintWriter printWriter;
	static Socket socket;
	static String chr = null;
	
	public Client(String chr ){
		this.chr = chr;		
	}
		
	public void run(){
		String line = null;
		String result = null;
		try{
			socket = new Socket(SERVERIP,SERVERPORT);
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
			printWriter = new PrintWriter(socket.getOutputStream(),true);
			printWriter.println(this.chr);
			}catch(Exception e){
				 e.printStackTrace();
				 }
         try{
		       while((line = bufferedReader.readLine())!=null){
					 result = line + "\n";
					 System.out.println(result);
			       }
             	printWriter.close();
				bufferedReader.close();
				socket.close();			   
		    }catch(IOException e){
			  e.printStackTrace();
			  }
				
		}
		
	public static void main(String[] args){
		chr = args[0];
		Thread thread = new Client(chr);
		thread.start();
	} 

  }

	
