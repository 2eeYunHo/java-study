package solostudy;

public class Penquin extends Animal {
	public String habit;
	
	public void printHabit() {
		System.out.println("고향은  " + habit);
	}
	public void printname() {
		System.out.println("알빠임 쓰레빠임??");
	}

	public void printname(String yourName) {
		System.out.println("안녕 " + yourName + "\t나는" + name);
	}

}
