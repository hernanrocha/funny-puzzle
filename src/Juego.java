import java.util.Vector;

public class Juego {

    private Tablero t;
    private Vector<GUIButton> botones;

    public void iniciarJuego() {
	t = new Tablero();
	t.initRandom();

	botones = new Vector<GUIButton>();
    }

    public int get(int i, int j) {
	return t.get(i, j);
    }

    public void click(int i, int j) {
	t = t.click(i, j);

	for (GUIButton boton : botones) {
	    boton.updateUI();
	}
    }

    public boolean estaResuelto() {
	return t.isValid();
    }

    public void agregarBoton(GUIButton boton) {
	botones.addElement(boton);
    }

    public Vector<Integer> encontrarSolucion() {
	// Tableros a visitar
	Vector<Tablero> posibles = new Vector<Tablero>();
	posibles.add(t);

	// Pasos anteriores
	Vector<Vector<Integer>> pasos = new Vector<Vector<Integer>>();
	Vector<Integer> v = new Vector<Integer>();
	v.addElement(-1);
	pasos.addElement(v);

	// Visitados
	Vector<Tablero> visitados = new Vector<Tablero>();

	while (posibles.size() > 0 && !posibles.firstElement().isValid()) {
	    // Pasar a visitado
	    Tablero tableroActual = posibles.firstElement();
	    visitados.addElement(tableroActual);
	    posibles.removeElementAt(0);

	    Vector<Integer> pasosActuales = pasos.firstElement();
	    pasos.removeElementAt(0);

	    // Agregar todos los hijos
	    for (int i = 0; i < Tablero.MATRIX_SIZE; i++) {
		for (int j = 0; j < Tablero.MATRIX_SIZE; j++) {
		    Tablero nuevoTablero = tableroActual.click(i, j);

		    if (!visitados.contains(nuevoTablero) && !posibles.contains(nuevoTablero)) {
			Vector<Integer> nuevosPasos = (Vector<Integer>) pasosActuales.clone();
			nuevosPasos.add(i * Tablero.MATRIX_SIZE + j + 1);

			posibles.addElement(nuevoTablero);
			pasos.addElement(nuevosPasos);
		    }
		}
	    }
	}

	if (posibles.size() > 0) {
	    Vector<Integer> secuencia = pasos.firstElement();
	    secuencia.removeElementAt(0);
	    return secuencia;
	}

	return null;
    }
}
