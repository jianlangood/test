public class TestOne implements Runnable{
//通过变量和方法传值
	private String str;
	public void setName(String str){
		this.str = str;
	}
	
	public void run(){
		System.out.println(this.str);
	}
	
	public static void main(String[] args){
		TestOne test = new TestOne();
		test.setName("CoCo");
		Thread thread = new Thread(test);
		thread.start();
	}
}