package prob04;
public class Prob04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] c1 = reverse( "Hello World" );
		printCharArray( c1 );
		
		char[] c2 = reverse( "Java Programming!" );
		printCharArray( c2 );
	}
	
	public static char[] reverse(String str) {
		/*
		 * - 문자열의 순서를 뒤집어서 char 배열로 리턴하는 메소드를 구현한다 - 
		 예를 들어 “Hi!” 라는 문자열을 파라미터로 전달 하면
		 * {‘!’, ‘i’, ‘H’ } 배열을 반환 한다.
		 * 
		 */
		/* 코드를 완성합니다 */
		String[] str2 = str.split(""); 
		String[] str3 = new String[str.length()];
		char[] temp = new char[str.length()];
		
		for(int i=0;i<str2.length;i++) {
			str3[i]=str2[(str2.length-1)-i];
			temp[i] = str3[i].charAt(0);

		}

		return temp;
	}

	public static void printCharArray(char[] array){
		/* 코드를 완성합니다 */

		System.out.println( array );
	}

}