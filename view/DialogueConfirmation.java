package fr.imie.gestionepicerie.view;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * classe qui créer un ticket de caisse
 *
 */
@SuppressWarnings("serial")
public abstract class DialogueConfirmation extends JDialog {
	
	// attribut
	private JFrame parent;
    private JLabel titre;

	// constructeur
	public DialogueConfirmation(JFrame fenetre) {
		super(fenetre, true);
		this.parent = fenetre;
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.titre = new JLabel("Voulez-vous quitter l'application ?");
		this.titre.setAlignmentY(JComponent.CENTER_ALIGNMENT);
		this.titre.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		this.getContentPane().add(titre);
		this.addButtons();
		this.pack();
	}

	private void addButtons() {
		JPanel panel = new JPanel();

		// non
		JButton non = new JButton("non");
		non.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DialogueConfirmation.this.dispose();
			}
		});
		panel.add(non);

		// oui
		JButton oui = new JButton("oui");
		oui.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DialogueConfirmation.this.onConfirm();
			}
		});
		panel.add(oui);

		this.getContentPane().add(panel);
	}

	public void onConfirm() {}

	public void center() {
		Point origin = parent.getLocation();
		int x = (int) (origin.getX() + parent.getWidth() / 2 - this.getWidth() / 2);
		int y = (int) (origin.getY() + parent.getHeight() / 2 - this.getHeight() / 2);
		this.setLocation(x, y);
	}

}