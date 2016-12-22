package fr.imie.gestionepicerie.view;
import fr.imie.gestionepicerie.model.VenteModel;
import fr.imie.gestionepicerie.model.ListeArticleModel;
import fr.imie.gestionepicerie.model.ListePanierModel;
import fr.imie.gestionepicerie.controller.EpicerieController;
import fr.imie.gestionepicerie.exception.BusinessException;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class VentePanel extends CentralPanel {
    
    private ListeArticleModel listeArticleModel;
	private ListePanierModel listePanierModel;
	private ArticleFormPanel articleForm;
	private VenteFormPanel venteForm;

    public VentePanel(){
        super();
        
        venteForm = new VenteFormPanel(this);
        
        this.rightPart.add(venteForm);
        
        addPart("Article");
        addPart("Panier");
        selectedPart = "Article";
        onChangePart();
        
    }

    @Override
    public void onChangePart(){
        updateTable();
        if (selectedPart.equals("Article")) {
            tableContainer.setBorder(BorderFactory.createTitledBorder("Tableau des Articles"));

        } else if (selectedPart.equals("Panier")) {
            tableContainer.setBorder(BorderFactory.createTitledBorder("Tableau des Paniers"));
        }
    }

    @Override
    public void onSelectRow(int row){
		if (selectedPart.equals("Article")) {
            VenteModel vente = new VenteModel();
            vente.setArticle( listeArticleModel.getArticle(row) );
            vente.setDate( new Date() );
			venteForm.setModel( vente );
		} 
        //~ else if (selectedPart.equals("Panier")) {
			//~ lotForm.setModel(listePanierModel.getPanier(row));
		//~ }
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
			listeArticleModel = new ListeArticleModel(
                EpicerieController.getInstance().getListArticles());
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
			listeArticleModel = new ListeArticleModel(
                EpicerieController.getInstance().getArticlesByRef(ref));
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
			listePanierModel = new ListePanierModel(
                EpicerieController.getInstance().getListPaniers());
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
			listePanierModel = new ListePanierModel(
                EpicerieController.getInstance().getPaniersByRef(ref));
			table.setModel(listePanierModel);
			refresh();
			// }catch (BusinessException e){
			// onError(e);
		} catch (Exception e) {
			onError(e);
		}
	}
    
	// ***************************************** exception ***************************************

	private void onError(BusinessException e) {
		JOptionPane.showMessageDialog(this, "Erreur : BusinessException dans VentePanel");
	}
    
	private void onError(Exception e) {
		// ~ logger.error(e.getMessage(),e);
		JOptionPane.showMessageDialog(this, "Erreur : Exception dans VentePanel : " + e.getMessage());
	}
    
}