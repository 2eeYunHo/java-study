package abstracted;

public class LettuceDecorator extends ToppingDecorator {
	public LettuceDecorator(Sandwich sandwich){
        super(sandwich);
    }

    public void make(){
        super.make();
        addLettuce();
    }

    private void addLettuce(){
        System.out.println(" + μμμΆ");
    }
}
