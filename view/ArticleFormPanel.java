package fr.imie.gestionepicerie.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.imie.gestionepicerie.controller.EpicerieController;
import fr.imie.gestionepicerie.exception.BusinessException;
import fr.imie.gestionepicerie.model.ArticleModel;

@SuppressWarnings("serial")
public class ArticleFormPanel extends JPanel {

	private CentralPanel parent;
	private boolean bool = false;
	private JComboBox<String> choixUnite;
	private JTextField reference;
	private JTextField prixAchat;
	private JTextField prixVente;
	private JTextField libelle;
	/**
	 * constructeur avec parametre
	 * 
	 * @param parent
	 */
	public ArticleFormPanel(final StockPanel parent) {
		this.parent = parent;
		choixUnite = new JComboBox<>();
		reference = new JTextField(10);
		prixAchat = new JTextField(10);
		prixVente = new JTextField(10);
		libelle = new JTextField(10);

		/**
		 * ************************************* infobulles
		 */
		reference.setToolTipText("Constitué d'une lettre suivit de deux chiffres ");
		prixAchat.setToolTipText("Prix d'achat auprès du fournisseur");
		prixVente.setToolTipText("Prix de vente auprès du client ");
		libelle.setToolTipText("Dénomination de l'article");
		choixUnite.setToolTipText("Unité de mesure pour l'article en fonction de sa categorie ");

		/**
		 * ************************************* jlabel et jtextfield
		 */

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel field = new JPanel();
		reference.setPreferredSize(new Dimension(03, 20));
		field.add(new JLabel("Référence : "));
		field.add(reference);
		field.add(new JLabel("(Max 3 caracteres)"));
		this.add(field);

		JPanel field1 = new JPanel();
		field1.add(new JLabel("Prix d'achat"));
		field1.add(prixAchat);
		// ~ field1.add(new JLabel("(Max 4 caracteres)"));
		this.add(field1);

		JPanel field2 = new JPanel();
		field2.add(new JLabel("Prix de vente : "));
		field2.add(prixVente);
		// ~ field2.add(new JLabel("(Max 4 caracteres)"));
		this.add(field2);

		JPanel field3 = new JPanel();
		field3.add(new JLabel("Libellé : "));
		field3.add(libelle);
		// ~ field3.add(new JLabel("(Max 10 carateres)"));
		this.add(field3);

		JPanel field4 = new JPanel();
		field4.add(new JLabel("Unité de mesure : "));
		field4.add(choixUnite);
		this.add(field4);

		choixUnite.addItem("Kilogramme (Kg)");
		choixUnite.addItem("Litre (L)");
		choixUnite.addItem("Unite (U)");
		/**
		 * *************************************** Jbuttons
		 */
		JPanel buttonPane = new JPanel();
		JButton nouveau = new JButton("Nouveau");
		JButton enregistrer = new JButton("Enregister");
		JButton supprimer = new JButton("Suppimer");
		/**
		 * *************************************** coloration et infobulles
		 */
		nouveau.setForeground(Color.WHITE);
		nouveau.setBackground(Color.BLUE);
		enregistrer.setBackground(Color.WHITE);
		supprimer.setForeground(Color.WHITE);
		supprimer.setBackground(Color.RED);
		nouveau.setToolTipText("réinitialise les champs ");
		enregistrer.setToolTipText("Si l'article existe déja il sera modifier sinon il sera crée ");
		supprimer.setToolTipText("Supprimer un article");

		buttonPane.add(nouveau);
		buttonPane.add(enregistrer);
		buttonPane.add(supprimer);
		/**
		 * ************************************** ajout des actionlistener sur
		 * les boutons
		 */
		nouveau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset();
				bool = false;

			}
		});

		enregistrer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArticleModel article = new ArticleModel();

				try {
					if (bool == false) {
						
						makeArticle(article);
						EpicerieController.getInstance().addArticle(article);
						reset();
						parent.updateTable();
					}

					else if (bool == true) {
						
						makeArticle(article);
						EpicerieController.getInstance().modifArticle(article, article.getReference());
						reset();
						parent.updateTable();
					}

				} catch (BusinessException e1) {
					// TODO Auto-generated catch block
					onError(e1);
				} catch (Exception e2) {
					onError(e2);
				}
			}
		});

		supprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (reference.getText().equals("") ){
					JOptionPane.showMessageDialog(parent, "veuillez selectionnez un article");
				}
					EpicerieController.getInstance().delArticle(reference.getText());
					reset()	;
					parent.updateTable();
			
				
			}
		});
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBorder(BorderFactory.createTitledBorder("Formulaire Article"));
		this.add(buttonPane);
		
	}



	/**
	 * methode pour vider les champs de saisie
	 */
	public void reset() {
		reference.setText("");
		prixAchat.setText("");
		prixVente.setText("");
		libelle.setText("");
		setModeModif(true);

	}

	/**
	 * exception
	 * 
	 * @param e
	 */


	private void onError(Exception e) {
		if (e.getMessage().contains("Duplicata")) {
			JOptionPane.showMessageDialog(this, "Erreur : reference deja utilisée ", "Warning" , JOptionPane.WARNING_MESSAGE);
		}
		
		switch (e.getMessage()) {
		case "empty String":
			JOptionPane.showMessageDialog(this, "Erreur : un des champs de saisie est vide ", "Warning" , JOptionPane.WARNING_MESSAGE);
			break;
		
		}
		
		System.out.println(e.getMessage());
	}

	/**
	 * methode pour recuperer le contenu des champ de saisie pour en faire un
	 * articleModel
	 * 
	 * @param article
	 * @throws BusinessException
	 */
	private void makeArticle(ArticleModel article) throws BusinessException {
		if (reference.getText().length() <= 3) {
			String ref = reference.getText();
			article.setReference(ref);
		}

		double achat = Double.parseDouble(prixAchat.getText());
		if (achat > 0) {
			article.setPrixAchat(achat);
		}
		double vente = Double.parseDouble(prixVente.getText());
		if (vente > 0) {
			article.setPrixVente(vente);
		}
		if (libelle.getText().length() <= 100) {
			article.setLibelle(libelle.getText());
		}
		if (choixUnite.getSelectedItem() == choixUnite.getItemAt(0)) {
			article.setUniteMesure("Kg");
		}
		if (choixUnite.getSelectedItem() == choixUnite.getItemAt(1)) {
			article.setUniteMesure("L");
		}
		if (choixUnite.getSelectedItem() == choixUnite.getItemAt(2)) {
			article.setUniteMesure("U");
		}

	}

	/**
	 * methode pour recuperer l'article selectionner dans le talbeau
	 * 
	 * @param article
	 */
	public void setModel(ArticleModel article) {
		reference.setText(article.getReference());
		prixAchat.setText(String.valueOf(article.getPrixAchat()));
		prixVente.setText(String.valueOf(article.getPrixVente()));
		libelle.setText(article.getLibelle());
		switch (article.getUniteMesure()) {
		case "Kg":
			choixUnite.setSelectedItem("Kilogramme (Kg)");
			break;
		case "L":
			choixUnite.setSelectedItem("Litre (L)");
			break;
		case "U":
			choixUnite.setSelectedItem("Unite (U)");
			break;

		}
		bool = true;
		
		this.setModeModif(false);
	}

	/**
	 * methode pour grisé les champ de saisie
	 * 
	 * @param bool
	 */
	public void setModeModif(boolean bool) {

		reference.setEnabled(bool);
		choixUnite.setEnabled(bool);
	}

}
