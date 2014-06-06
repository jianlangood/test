

  class Animal{
	private String name;
	Animal(String name){
		this.name = name;
	}
	public void enjoy(){
		System.out.println("voice...");
		}
	}
	
	class Cat extends Animal{
		private String eyesColor;
		Cat(String n,String color){
			super(n);
			this.eyesColor = color;
		}
		public void enjoy(){
			System.out.println("miao-miao ...");
		}
	}
	
	class Dog extends Animal{
		private String furColor;
		Dog(String n,String color){
			super(n);
			this.furColor = color;
		}
		public void enjoy(){
			System.out.println("wang-wang ...");
		}
	}
	 class Lady{
		private Animal pet;//对象类型
		private String name;
		Lady(Animal pet,String name){
			this.pet = pet;
			this.name = name;
		}
		public void petEnjoy(){
			pet.enjoy();
		}
	}
	public class Dynamic{
	public static void main(String[] args){
		Cat cat = new Cat("mimi","blue");
		Dog dog = new Dog("bei","black");
		Lady l1 = new Lady(cat,"c");
		l1.petEnjoy();
	}
}