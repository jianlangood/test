public class TestTwo extends Thread{
//使用回调函数进行参数传递
	private Work work;
	public TestTwo(Work work){
		this.work = work;
	}
	public void run(){
		java.util.Random random = new java.util.Random();
		Data data = new Data();
		int n1 = random.nextInt(1000); 
        int n2 = random.nextInt(2000); 
		int n3 = random.nextInt(3000); 
		work.process(data, n1, n2, n3); //使用回调函数
		System.out.println(String.valueOf(n1) + "+" + String.valueOf(n2) + "+" 
                            + String.valueOf(n3) + "=" + data.value); 
	}
	public static void main(String[] args){
		Thread thread = new TestTwo(new Work());
		thread.start();
	}
}

class Data{
	public int value = 0;
}

class Work{
	public void process(Data data,int a1,int a2,int a3){
		
			data.value = a1+a2+a3;
		}
	}
	
