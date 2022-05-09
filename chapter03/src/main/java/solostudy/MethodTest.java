package solostudy;

public class MethodTest {

	public static void main(String[] args) {
		call("Hey~", 3);
		sum(5,15);
	}

	public static void call(String str, int i) {
		str = "요거는?";
		i = 4;
		System.out.println("이게안돼?");
		System.out.println(str + i);
	}

	public static void sum(int a, int b) {
		int c = a+b;
		System.out.println(c);
	}
}
