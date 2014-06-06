import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class ServerLiao  {
	
	private static final int SERVERPORT = 54321;
	private static List<Socket> clientList = new ArrayList<Socket>();//客户端连接
	private ServerSocket ss = null;
	private ExecutorService executorService;//定义线程池
    Socket client = null;
    public static void main(String[] args){
	   new ServerLiao();
   }
   
   public ServerLiao(){
	  try{ 
		  ss = new ServerSocket(SERVERPORT);
	      executorService = Executors.newCachedThreadPool();//创建一个线程池
	      System.out.println("start...");
	   //用来临时保存客户端连接的socket  
	      while(true){
	    	  client = ss.accept();
	    	  System.out.println("accept");
	    	  clientList.add(client);
	    	  executorService.execute(new ThreadServer(client));
	   }
	  }catch(IOException e){
		  e.printStackTrace();
	  }
   }
   
   static class ThreadServer implements Runnable{
	   
	   private Socket msocket;
	   private BufferedReader bufferedReader;
	   private PrintWriter printWriter;
	   private String strMsg;
	   
	   public ThreadServer(Socket socket) throws IOException{
		   this.msocket  = socket;
		   bufferedReader = new BufferedReader(new InputStreamReader(this.msocket.getInputStream()));
		   strMsg = "user:" + this.msocket.getInetAddress() +"  come total:" + clientList.size();
		   sendMessage();
	   }
	   
	   public void run(){
		   try{
		       while((strMsg = bufferedReader.readLine())!=null){
			     if(strMsg.trim().equals("exit")){
	//				   当一个客户端退出时
					   clientList.remove(msocket);
					   bufferedReader.close();
					   printWriter.close();
					   strMsg = "user:"+this.msocket.getInetAddress()+"   exit total:"+clientList.size();
					   msocket.close();
					   sendMessage();
					   break;
			       }
			   else{
				   strMsg = "message--->"+msocket.getInetAddress()+":"+strMsg;
				   sendMessage();
			   }
		    }
		  }catch(IOException e){
			  e.printStackTrace();
			  }
	   }
//	   发送信息给客户端
	   private void sendMessage() throws IOException{
		   System.out.println(strMsg);
		   //for(循环变量类型  循环变量名称：要被循环遍历的对象)
		   for(Socket client:clientList){
			   printWriter = new PrintWriter(client.getOutputStream(),true);
			   printWriter.println(strMsg);
		   }
	   }
   }
}
