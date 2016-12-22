package fr.imie.gestionepicerie.view;

import fr.imie.gestionepicerie.model.ListeArticleModel;
import fr.imie.gestionepicerie.model.ListeLotModel;
import fr.imie.gestionepicerie.controller.EpicerieController;
import fr.imie.gestionepicerie.exception.BusinessException;
import javax.swing.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class StockPanel extends CentralPanel {
    
    private ListeArticleModel listeArticleModel;
	private ListeLotModel listeLotModel;
	private ArticleFormPanel articleForm;
	private LotFormPanel lotForm;
    private JPanel formContainer;
    
    public StockPanel(){
        super();
        
        formContainer = new JPanel();
        lotForm  = new LotFormPanel(this);
		articleForm = new  ArticleFormPanel(this);
        
        formContainer.add(new JPanel());
		this.rightPart.add(formContainer);
        
        addPart("Article");
        addPart("Lot");
        selectedPart = "Article";
        onChangePart();
    }
    
    public void changeForm(JPanel form) {
		formContainer.remove(0);
		formContainer.add(form);
		refresh();
	}
    
    @Override
    public void onChangePart(){
        updateTable();
        if (selectedPart.equals("Article")) {
            tableContainer.setBorder(BorderFactory.createTitledBorder("Tableau des Articles"));
            changeForm(articleForm);

        } else if (selectedPart.equals("Lot")) {
            tableContainer.setBorder(BorderFactory.createTitledBorder("Tableau des Lots"));
            changeForm(lotForm);
        }
    }

    @Override
    public void onSelectRow(int row){
		if (selectedPart.equals("Article")) {
			articleForm.setModel(listeArticleModel.getArticle(row));
		} else if (selectedPart.equals("Lot")) {
			lotForm.setModel(listeLotModel.getLot(row));
		}
	}

    @Override
    public void updateTable() {
		if (selectedPart.equals("Article")) {
			updateArticleTableau();
		} else if (selectedPart.equals("Lot")) {
			updateLotTableau();
		}
	}
    
    @Override
    public void updateTableFromRef(String ref) {
		if (selectedPart.equals("Article")) {
			updateArticleTableauFromRef(ref);
		} else if (selectedPart.equals("Lot")) {
			updateLotTableauFromRef(ref);
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

	public void updateLotTableau() {
		try {
			listeLotModel = new ListeLotModel(
                EpicerieController.getInstance().getListLots());
			table.setModel(listeLotModel);
			refresh();
			// }catch (BusinessException e){
			// onError(e);
		} catch (Exception e) {
			onError(e);
		}
	}

	public void updateLotTableauFromRef(String ref) {
		try {
			listeLotModel = new ListeLotModel(
                EpicerieController.getInstance().getLotsByRef(ref));
			table.setModel(listeLotModel);
			refresh();
			// }catch (BusinessException e){
			// onError(e);
		} catch (Exception e) {
			onError(e);
		}
	}
    
	// ***************************************** exception ***************************************

	private void onError(BusinessException e) {
		JOptionPane.showMessageDialog(this, "Erreur : BusinessException dans StockPanel");
	}
    
	private void onError(Exception e) {
		// ~ logger.error(e.getMessage(),e);
		JOptionPane.showMessageDialog(this, "Erreur : Exception dans StockPanel : " + e.getMessage());
	}
}