public class Test extends Thread {
//通过构造函数传值	
	static String chr = null;
	public Test(String chr){
	this.chr = chr;
	}
	public void run (){
	System.out.println("hello"+this.chr);
	}
public static void main(String[] args){
	    chr = args[0];
	    Thread thread = new Test(chr);
	    thread.start();
	}
	
}