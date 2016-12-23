package fr.imie.gestionepicerie.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import fr.imie.gestionepicerie.controller.EpicerieController;
import fr.imie.gestionepicerie.exception.BusinessException;
import fr.imie.gestionepicerie.model.ListeArticleModel;
import fr.imie.gestionepicerie.model.ListePanierModel;
import fr.imie.gestionepicerie.model.PanierModel;
import fr.imie.gestionepicerie.model.VenteModel;

@SuppressWarnings("serial")
public class VentePanel extends CentralPanel {

	private ListeArticleModel listeArticleModel;
	private ListePanierModel listePanierModel;
	private ArticleFormPanel articleForm;
	private VenteFormPanel venteForm;
	private VenteModel venteModel;
	private PanierModel panierModel;
	private PanierPanel panierPanel;
	private JButton openPanier;

	public VentePanel() {
		super();

		venteForm = new VenteFormPanel(this);
		panierPanel = new PanierPanel(this);

		this.rightPart.add(venteForm);
		this.rightPart.add(panierPanel);
		
		openPanier = new JButton();
		makePanierButton();
		openPanier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onClickInitPanier();
			}
		});

		addPart("Article");
		addPart("Panier");
		selectedPart = "Article";
		onChangePart();

	}

	public void onClickInitPanier() {
		openPanier.setVisible(false);
		panierPanel.setVisible(true);
		venteForm.setVisible(true);
		// ~ panierModel = EpicerieController.getInstance().createPanier();
		// ~ panierPanel.setModel( panierModel );

	}

	public void addVenteToPanier(VenteModel vente) {
		System.out.println("addVenteToPanier : " + vente);
	}

	public void validPanier() {
		System.out.println("validPanier");
	}

	public void delPanier() {
		System.out.println("delPanier");
	}

	public void delVenteFromPanierByLineNumber(int line) {
		System.out.println("delVenteFromPanierByLineNumber : " + line);
	}

	@Override
	public void onChangePart() {
		updateTable();
		if (selectedPart.equals("Article")) {
			tableContainer.setBorder(BorderFactory.createTitledBorder("Tableau des Articles"));

		} else if (selectedPart.equals("Panier")) {
			tableContainer.setBorder(BorderFactory.createTitledBorder("Tableau des Paniers"));
		}
	}

	@Override
	public void onSelectRow(int row) {
		if (selectedPart.equals("Article")) {
			venteModel = new VenteModel();
			venteModel.setArticle(listeArticleModel.getArticle(row));
			venteModel.setDate(new Date());
			venteForm.setModel(venteModel);
		}
		// ~ else if (selectedPart.equals("Panier")) {
		// ~ lotForm.setModel(listePanierModel.getPanier(row));
		// ~ }
	}

	@Override
	public void updateTable() {
		if (selectedPart.equals("Article")) {
			updateArticleTableau();
		} else if (selectedPart.equals("Panier")) {
			updatePanierTableau();
		}
	}

	@Override
	public void updateTableFromRef(String ref) {
		if (selectedPart.equals("Article")) {
			updateArticleTableauFromRef(ref);
		} else if (selectedPart.equals("Panier")) {
			updatePanierTableauFromRef(ref);
		}
	}

	public void updateArticleTableau() {
		try {
			listeArticleModel = new ListeArticleModel(EpicerieController.getInstance().getListArticles());
			table.setModel(listeArticleModel);
			refresh();
		} catch (BusinessException e) {
			onError(e);
		} catch (Exception e) {
			onError(e);
		}
	}

	public void updateArticleTableauFromRef(String ref) {
		try {
			listeArticleModel = new ListeArticleModel(EpicerieController.getInstance().getArticlesByRef(ref));
			table.setModel(listeArticleModel);
			refresh();
			// }catch (BusinessException e){
			// onError(e);
		} catch (Exception e) {
			onError(e);
		}
	}

	public void updatePanierTableau() {
		try {
			listePanierModel = new ListePanierModel(EpicerieController.getInstance().getListPaniers());
			table.setModel(listePanierModel);
			refresh();
			// }catch (BusinessException e){
			// onError(e);
		} catch (Exception e) {
			onError(e);
		}
	}

	public void updatePanierTableauFromRef(String ref) {
		try {
			listePanierModel = new ListePanierModel(EpicerieController.getInstance().getPaniersByRef(ref));
			table.setModel(listePanierModel);
			refresh();
			// }catch (BusinessException e){
			// onError(e);
		} catch (Exception e) {
			onError(e);
		}
	}

	public void makePanierButton() {
		venteForm.setVisible(false);
		panierPanel.setVisible(false);
		openPanier.setText("Nouveau Panier");
		//~ try {
			//~ Image img = ImageIO.read(new File("img/panier.jpg"));
			//~ openPanier.setIcon(new ImageIcon(img));
		//~ } catch (IOException ex) {
			//~ ex.printStackTrace();
		//~ }
		openPanier.setPreferredSize(new Dimension(375, 100));
		openPanier.setBounds(80, 80, 80, 80);
		openPanier.setVerticalTextPosition(SwingConstants.TOP);
		openPanier.setHorizontalTextPosition(SwingConstants.CENTER);
		this.rightPart.add(openPanier);
	}

	// ***************************************** exception
	// ***************************************

	private void onError(BusinessException e) {
		JOptionPane.showMessageDialog(this, "Erreur : BusinessException dans VentePanel");
	}

	private void onError(Exception e) {
		// ~ logger.error(e.getMessage(),e);
		JOptionPane.showMessageDialog(this, "Erreur : Exception dans VentePanel : " + e.getMessage());
	}

}
