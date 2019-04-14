import java.util.Arrays;

public class Tablero {
    public static final int MATRIX_SIZE = 4;
    public static final int MAX_VALUES = 2;

    // Datos del tablero
    int[][] m = new int[MATRIX_SIZE][];

    public Tablero() {
	// Inicializacion
	for (int i = 0; i < m.length; i++) {
	    m[i] = new int[MATRIX_SIZE];
	}
    }

    public void initRandom() {
	m[0][1] = 1;
	m[1][3] = 1;
	m[3][0] = 1;	
	
	/*
	for (int i = 0; i < m.length; i++) {
	    for (int j = 0; j < m[i].length; j++) {
		m[i][j] = (int) (Math.random() * 2);
	    }
	}
	*/
    }

    public int get(int i, int j) {
	if (i < 0 || i > (m.length - 1) || j < 0 || j > (m[i].length - 1))
	    return -1;

	return m[i][j];
    }

    public void set(int i, int j, int value) {
	if (i < 0 || i > (m.length - 1) || j < 0 || j > (m[i].length - 1))
	    return;

	m[i][j] = value;
    }

    public Tablero makeCopy() {
	Tablero t = new Tablero();
	for (int i = 0; i < m.length; i++) {
	    for (int j = 0; j < m[i].length; j++) {
		t.set(i, j, get(i, j));
	    }
	}
	return t;
    }

    public Tablero click(int i, int j) {
	Tablero t = makeCopy();

	t.set(i, j, next(get(i, j))); // Cambiar el propio
	t.set(i - 1, j, next(get(i - 1, j))); // Cambiar arriba
	t.set(i + 1, j, next(get(i + 1, j))); // Cambiar abajo
	t.set(i, j - 1, next(get(i, j - 1))); // Cambiar izquierdo
	t.set(i, j + 1, next(get(i, j + 1))); // Cambiar derecho

	return t;
    }

    public boolean isValid() {
	for (int i = 0; i < m.length; i++) {
	    for (int j = 0; j < m[i].length; j++) {
		if (get(i, j) != 0) {
		    return false;
		}
	    }
	}

	return true;
    }

    public void print() {
	for (int i = 0; i < m.length; i++) {
	    for (int j = 0; j < m[i].length; j++) {
		System.out.print(" " + get(i, j));
	    }
	    System.out.print("\n");
	}
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Arrays.deepHashCode(m);
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Tablero other = (Tablero) obj;
	if (!Arrays.deepEquals(m, other.m))
	    return false;
	return true;
    }

    private int next(int i) {
	return (i + 1) % MAX_VALUES;
    }
}
