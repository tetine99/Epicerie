package fr.imie.gestionepicerie.view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.imie.gestionepicerie.model.LotModel;

public class AffichageLotPanel extends JPanel{
	private JLabel id = new JLabel();
	private JLabel libelle = new JLabel();
	private JLabel prixAchat = new JLabel();
	private JLabel prixVente = new JLabel();
	private JLabel datePremption = new JLabel();
	
	public void build(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(id);
		this.add(libelle);
		this.add(prixAchat);
		this.add(prixVente);
		this.add(datePremption);
	}
	
	public void build(LotModel lot){
		this.id.setText(String.format("Référence : %s", lot.getId()));
		this.libelle.setText(String.format("libéllé : %s", lot.getLibelle()));
		this.prixAchat.setText(String.format("prix Achat : %s", lot.getPrixAchat()));
		this.prixVente.setText(String.format("Prix vente : %s ", lot.getPrixVente()));
		this.datePremption.setText(String.format("date de pé remption : %s", lot.getDatePeremption()));
		
	}
}
