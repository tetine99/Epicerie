package fr.imie.gestionepicerie.view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.imie.gestionepicerie.model.ArticleModel;

public class AffichageArticlePanel extends JPanel {
	
	private JLabel reference = new JLabel();
	private JLabel libelle = new JLabel();
	private JLabel prixAchat = new JLabel();
	private JLabel prixVente = new JLabel();
	
	
	public void build(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(reference);
		this.add(libelle);
		this.add(prixAchat);
		this.add(prixVente);
	
	}
	public void build(ArticleModel article){
		this.reference.setText(String.format("Référence ; %s", article.getReference()));
		this.libelle.setText(String.format("Libéllé : %s", article.getLibelle()));
		this.prixAchat.setText(String.format("PrixAchat : %s", article.getPrixAchat()));
		this.prixVente.setText(String.format("Prix ventre : %s", article.getPrixVente()));
	}

}
