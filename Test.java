
public class Test {

	private int number = 7;

	public Test() {
		dostuff();
	}


	private void dostuff1() {

	}

	private void dostuff2() {

	}

	private void dostuff3() {

	}

	public static void main(String[] args) {
		new Test();
	}

	private void dostuff() {
		if (number == 7) {
			dostuff1();
		}

		if (number != 8) {
			dostuff3();
		} else if (number != 9) {
			dostuff2();
		}
	}

}