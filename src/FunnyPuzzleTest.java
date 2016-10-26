
public class FunnyPuzzleTest {

    public static void main(String[] args) {
	Tablero t = new Tablero();

	t.initRandom();
	t.print();
	System.out.println();

	t = t.click(1, 1);
	t.print();
	System.out.println();

	t = t.click(0, 0);
	t.print();
	System.out.println();

	Tablero t1 = t;
	t = t.click(2, 1);
	t.print();
	System.out.println();

	System.out.println(t1.equals(t));
	t = t.click(2, 1);
	System.out.println(t1.equals(t));
    }

}
