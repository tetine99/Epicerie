package fr.imie.gestionepicerie.view;

import fr.imie.gestionepicerie.controller.EpicerieController;
import fr.imie.gestionepicerie.exception.BusinessException;
import fr.imie.gestionepicerie.model.ArticleModel;
import fr.imie.gestionepicerie.model.LotModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LotFormPanel extends JPanel {
	private JTextField dateAchat;
	private JTextField datePeremption;
	private JTextField quantite;
	private JTextField refArticle;
	private JPanel buttonPane;
	private JButton nouveau;
	private JButton enregistrer;
	private JButton supprimer;
	private Date date;
	private StockPanel parent;
	private LotModel lotModel;
	private boolean bool = false;
	private int id = 0;
	SimpleDateFormat dateFormat;

	public LotFormPanel(StockPanel parent) {
		this.parent = parent;
		dateAchat = new JTextField(10);
		datePeremption = new JTextField(10);
		quantite = new JTextField(3);
		refArticle = new JTextField(3);
		buttonPane = new JPanel();
		nouveau = new JButton("Nouveau");
		enregistrer = new JButton("Enregistrer");
		supprimer = new JButton("Suppimer");
		date = new Date();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		reset();

		/**
		 * ************************************ infobulles et cadres
		 */
		refArticle.setToolTipText("Reference de l'article contenu dans le lot ");
		dateAchat.setToolTipText("Prix d'achat auprès du fournisseur");
		datePeremption.setToolTipText("Date de peremption de l'article ");
		quantite.setToolTipText("Quantité de l'article dans le lot");
		nouveau.setToolTipText("réinitialise les champs ");
		enregistrer.setToolTipText("Si le lot existe déja il sera modifier sinon il sera crée ");
		supprimer.setToolTipText("Supprimer un lot");
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBorder(BorderFactory.createTitledBorder("Formulaire lot"));
		// *************************** jtextfield et jlabel
		// ************************************
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel field = new JPanel();
		field.add(new JLabel("Date d'achat"));
		field.add(dateAchat);
		this.add(field);

		JPanel field1 = new JPanel();
		field1.add(new JLabel("Date de Péremption : "));
		field1.add(datePeremption);
		field1.add(new JLabel("(JJ/MM/AAAA)"));
		this.add(field1);

		JPanel field2 = new JPanel();
		field2.add(new JLabel("Quantité : "));
		field2.add(quantite);
		field2.add(new JLabel("(Max 3 caractères)"));
		this.add(field2);

		JPanel field3 = new JPanel();
		field3.add(new JLabel("Référence de l'article"));
		field3.add(refArticle);
		field3.add(new JLabel("(Max 3 caractères)"));
		this.add(field3);
		// ****************************** ajout des action listener sur les
		// boutons *********************************
		nouveau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				reset();
				setModeModif(true);
				bool = false;
			}
		});

		enregistrer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					ArticleModel article = new ArticleModel();
					LotModel lot = new LotModel();
					article.setReference(refArticle.getText());
					lot.setArticle(article);

					Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateAchat.getText());
					Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(datePeremption.getText());

					lot.setDateAchat(date1);
					lot.setDatePeremption(date2);

					lot.setQuantite(Double.parseDouble(quantite.getText()));

					if (bool == false) {

						EpicerieController.getInstance().addLot(lot);
						parent.updateLotTableau();
					}

					else if (bool == true) {
						lot.setIdLot(id);
						EpicerieController.getInstance().modifLot(lot, lot.getIdLot());
						parent.updateLotTableau();
					}

				} catch (Exception e) {
					onError(e);
				}
			}

		}

		);
		supprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				EpicerieController.getInstance().delLot(lotModel.getIdLot());
				parent.updateLotTableau();

			}
		});

		buttonPane.add(nouveau);
		buttonPane.add(enregistrer);
		buttonPane.add(supprimer);

		this.add(buttonPane);
	}

	public LotFormPanel() {
		dateAchat = new JTextField(10);
		datePeremption = new JTextField(10);
		quantite = new JTextField(3);
		refArticle = new JTextField(3);
		buttonPane = new JPanel();
		nouveau = new JButton("Nouveau");
		enregistrer = new JButton("Enregistrer");
		supprimer = new JButton("Suppimer");
		date = new Date();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		reset();

		/**
		 * ************************************ infobulles et cadres
		 */
		refArticle.setToolTipText("Reference de l'article contenu dans le lot ");
		dateAchat.setToolTipText("Prix d'achat auprès du fournisseur");
		datePeremption.setToolTipText("Date de peremption de l'article ");
		quantite.setToolTipText("Quantité de l'article dans le lot");
		nouveau.setToolTipText("réinitialise les champs ");
		enregistrer.setToolTipText("Si le lot existe déja il sera modifier sinon il sera crée ");
		supprimer.setToolTipText("Supprimer un lot");
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBorder(BorderFactory.createTitledBorder("Formulaire lot"));
		// *************************** jtextfield et jlabel
		// ************************************
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel field = new JPanel();
		field.add(new JLabel("Date d'achat"));
		field.add(dateAchat);
		this.add(field);

		JPanel field1 = new JPanel();
		field1.add(new JLabel("Date de Péremption : "));
		field1.add(datePeremption);
		field1.add(new JLabel("(JJ/MM/AAAA)"));
		this.add(field1);

		JPanel field2 = new JPanel();
		field2.add(new JLabel("Quantité : "));
		field2.add(quantite);
		field2.add(new JLabel("(Max 3 caractères)"));
		this.add(field2);

		JPanel field3 = new JPanel();
		field3.add(new JLabel("Référence de l'article"));
		field3.add(refArticle);
		field3.add(new JLabel("(Max 3 caractères)"));
		this.add(field3);
		// ****************************** ajout des action listener sur les
		// boutons *********************************
		nouveau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				reset();
				setModeModif(true);
				bool = false;
			}
		});

		enregistrer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					ArticleModel article = new ArticleModel();
					LotModel lot = new LotModel();
					article.setReference(refArticle.getText());
					lot.setArticle(article);

					Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateAchat.getText());
					Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(datePeremption.getText());

					lot.setDateAchat(date1);
					lot.setDatePeremption(date2);

					lot.setQuantite(Double.parseDouble(quantite.getText()));

					if (bool == false) {

						EpicerieController.getInstance().addLot(lot);
						parent.updateLotTableau();
					}

					else if (bool == true) {
						lot.setIdLot(id);
						EpicerieController.getInstance().modifLot(lot, lot.getIdLot());
						parent.updateLotTableau();
					}

				} catch (Exception e) {
					onError(e);
				}
			}

		}

		);
		supprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				EpicerieController.getInstance().delLot(lotModel.getIdLot());
				parent.updateLotTableau();

			}
		});

		buttonPane.add(nouveau);
		buttonPane.add(enregistrer);
		buttonPane.add(supprimer);

		this.add(buttonPane);
	}

	public void reset() {
		dateAchat.setText(dateFormat.format(date));
		refArticle.setText("");
		quantite.setText("");
		datePeremption.setText("");

	}

	/**
	 * methode pour grisé les champ de saisie
	 * 
	 * @param bool
	 */
	public void setModeModif(boolean bool) {
		dateAchat.setEnabled(bool);
		refArticle.setEnabled(bool);

	}

	/**
	 * methode pour recuperer le lot selectionner dans le tableau
	 * 
	 * @param lot
	 */
	public void setModel(LotModel lot) {
		lotModel = lot;

		dateAchat.setText(dateFormat.format(lot.getDateAchat()));
		datePeremption.setText(dateFormat.format(lot.getDatePeremption()));
		quantite.setText(String.valueOf(lot.getQuantite()));
		refArticle.setText(lot.getArticle().getReference());
		id = lot.getIdLot();
		System.out.println("LotForm : " + lot);
		setModeModif(false);
		bool = true;

	}

	public void refresh() {
		StockPanel panel = new StockPanel();
		panel.updateLotTableau();
	}

	/**
	 * gestion des erreur
	 * 
	 * @param e
	 */
	@SuppressWarnings("unused")
	private void onError(BusinessException e) {
		switch (e.getCode()) {
		case "reference.blank":
			JOptionPane.showMessageDialog(this, "Erreur : champ reference incorrect ");
			break;

		case "libelle.blank":
			JOptionPane.showMessageDialog(this, "Erreur : champ libelle incorrect ");
			break;

		case "reference.duplicate":
			JOptionPane.showMessageDialog(this, "Erreur : reference deja utilisée ");
			break;

		case "prixAchat.blank":
			JOptionPane.showMessageDialog(this, "Erreur : champ prix achat incorrect ");
			break;
		case "prixVente.blank":
			JOptionPane.showMessageDialog(this, "Erreur : champ prix vente incorrect ");
		}

	}

	private void onError(Exception e) {

		JOptionPane.showMessageDialog(this, "Erreur : impossible de créer l'article");
		System.out.println(e.getMessage());
	}

}
