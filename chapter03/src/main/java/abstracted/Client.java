package abstracted;

public class Client {

	public static void main(String[] args) {
		Sandwich burger = new Bread();
		burger.make();
		System.out.println("-------------");
		Sandwich sandwichWithLettuce = new LettuceDecorator(new Bread());
        sandwichWithLettuce.make();
        System.out.println("-------");

        // 양상추+피클 샌드위치
        Sandwich sandwichWithLettuceAndPickle = new PickleDecorator(new LettuceDecorator(new Bread()));
        sandwichWithLettuceAndPickle.make();
        
	}

}
