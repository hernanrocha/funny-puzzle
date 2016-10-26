import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GUIButton extends JButton {

    private static final long serialVersionUID = 8903828142772539485L;
    private int i;
    private int j;
    private Juego juego;

    public GUIButton(Juego juego, int i, int j) {
	super("" + (i * Tablero.MATRIX_SIZE + j + 1));
	setContentAreaFilled(false);
	setFocusPainted(false);
	setFont(new Font("Nirmala UI", Font.BOLD, 50));
	setForeground(Color.WHITE);

	this.i = i;
	this.j = j;
	this.juego = juego;

	addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		juego.click(i, j);
	    }
	});

	juego.agregarBoton(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
	Color c1, c2;

	if (juego.get(i, j) == 0) {
	    c1 = new Color(0, 128, 0);
	    c2 = new Color(0, 158, 0);
	} else if (juego.get(i, j) == 1){
	    c1 = new Color(174, 0, 0);
	    c2 = new Color(204, 0, 0);
	} else {
	    c1 = new Color(0, 0, 174);
	    c2 = new Color(0, 0, 204);
	}

	final Graphics2D g2 = (Graphics2D) g.create();
	g2.setPaint(new GradientPaint(new Point(0, 0), c1, new Point(0, getHeight()), c2));
	g2.fillRect(0, 0, getWidth(), getHeight());
	g2.dispose();

	super.paintComponent(g);
    }
}
