package prob3;

public abstract class Bird {
	protected String name;
	abstract void sing();
	abstract void fly();
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}