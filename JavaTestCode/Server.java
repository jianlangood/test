import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	ServerSocket ss = null;
	Socket socket = null;
	String  c = null;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			 ss = new ServerSocket(54321);
			while(true){
			    socket = ss.accept();
				System.out.println("accept");
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String line = bufferedReader.readLine();
				System.out.println("read=" + line);
				
				
				PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
				pw.println("server message");
				
				bufferedReader.close();
				pw.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("close");
		}
		
	}
	
   public static void main(String[] args){
	   Thread thread = new Thread(new Server());
	   thread.start();
	   
   }
}
