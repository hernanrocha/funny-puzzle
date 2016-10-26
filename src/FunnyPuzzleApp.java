import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FunnyPuzzleApp {

	private JFrame frmResuelveElAcertijo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FunnyPuzzleApp window = new FunnyPuzzleApp();
					window.frmResuelveElAcertijo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FunnyPuzzleApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Juego juego = new Juego();
		juego.iniciarJuego();
		
		frmResuelveElAcertijo = new JFrame();
		frmResuelveElAcertijo.setTitle("Resuelve el acertijo!");
		BorderLayout borderLayout = (BorderLayout) frmResuelveElAcertijo.getContentPane().getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		frmResuelveElAcertijo.setType(Type.UTILITY);
		frmResuelveElAcertijo.setResizable(false);
		frmResuelveElAcertijo.setBounds(100, 100, 450, 500);
		frmResuelveElAcertijo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel panel = new Panel();
		frmResuelveElAcertijo.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton btnEncontrarSolucion = new JButton("Resolver");
		btnEncontrarSolucion.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		panel.add(btnEncontrarSolucion);
		
		Label labelSolucion = new Label("");
		labelSolucion.setForeground(new Color(0, 128, 0));
		labelSolucion.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		labelSolucion.setAlignment(Label.CENTER);
		panel.add(labelSolucion);
		
		Label labelUnicen = new Label("\u00A9 Facultad de Cs. Exactas, UNICEN - 2016");
		panel.add(labelUnicen);
		labelUnicen.setForeground(new Color(0, 0, 0));
		labelUnicen.setFont(new Font("Calibri Light", Font.BOLD, 12));
		labelUnicen.setAlignment(Label.CENTER);
		btnEncontrarSolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<Integer> secuencia = juego.encontrarSolucion();
				labelSolucion.setText("Solucion: " + secuencia);
			}
		});
		
		JPanel panelGame = new JPanel();
		frmResuelveElAcertijo.getContentPane().add(panelGame, BorderLayout.CENTER);
		panelGame.setLayout(new GridLayout(Tablero.MATRIX_SIZE, Tablero.MATRIX_SIZE, 3, 3));
		
		for (int i = 0; i < Tablero.MATRIX_SIZE; i++) {
			for (int j = 0; j < Tablero.MATRIX_SIZE; j++) {
				GUIButton btn = new GUIButton(juego, i, j);
				panelGame.add(btn);
			}
		}
	}

}
