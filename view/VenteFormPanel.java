package fr.imie.gestionepicerie.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.imie.gestionepicerie.model.ArticleModel;
import fr.imie.gestionepicerie.model.ListeVentePanierModel;

public class VenteFormPanel extends JPanel{

	public static void main(String[] args) {
		VenteFenetre fenetre = new VenteFenetre();
		fenetre.setVisible(true);
	}
	
	private ArticleModel article = new ArticleModel();
	private JTextField refArticle = new JTextField(10);
	private JTextField date = new JTextField(10);
	private JTextField quantite = new JTextField(10);
	private JLabel uniteMesure = new JLabel("unité : "+article.getUniteMesure());
	public void build(){
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel field = new JPanel();
		field.add(new JLabel("Référence de l'article : "));
		field.add(refArticle);
		
		this.add(field);
	
		JPanel field1 = new JPanel();
		field1.add(new JLabel("Date et heure : "));
		field1.add(date);
		this.add(field1);
		
		JPanel field2 = new JPanel();
		field2.add(new JLabel("Quantité : "));
		field2.add(quantite);
		field2.add(uniteMesure);
		this.add(field2);
		
		JButton ajouter = new JButton("Ajouter au panier");
		ajouter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		ajouter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		this.add(ajouter);
	}
}

