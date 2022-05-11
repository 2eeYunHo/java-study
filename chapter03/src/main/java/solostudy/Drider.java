package solostudy;

public class Drider {

	public static void main(String[] args) {
		Penquin peng1 = new Penquin();
		Animal dong1 = new Penquin();
		Animal me = new Animal();
		
		
		dong1.name = "me";
//		dong1.printHabit();
//	타입이 Penquin이 아닌 Animal으로 되어 하위클래스의 printHabit()을 가져올수 없음 쓰고싶으면 오버라이딩
		dong1.printname();//Animal을 상속받은 펭귄에서 Overidng한 printname();의 출력
//		dong1.printname("쓰레기");
//	타입이 Penquin이 아닌 Animal으로 되어 하위클래스의 printname()을 가져올수 없음 쓰고싶으면 오버라이딩

		
		peng1.habit = "집";
		peng1.name = " 이윤호";
		
		peng1.printHabit();	// peng1.habit
		peng1.printname(); // Penquin에서 Overriding한 printname으로 결과를 출력하게됨
		peng1.printname("누구냐"); //펭귄클래스의 오버라이드된 printname();를 파라미터값으로 확인하여 출력
		//peng1.name이 Penquin에전달되어 name으로출력 파리미터가 정의되지않은your네임으로 들어감
	


		me.name = "윤호";
//		me.printHabit(); 위내용과 같음
		me.printname(); // ㅇㅇ
//		me.printname("바보");

	}

}
