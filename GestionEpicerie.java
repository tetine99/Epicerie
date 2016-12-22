package fr.imie.gestionepicerie;
import javax.swing.ToolTipManager;

import fr.imie.gestionepicerie.controller.EpicerieController;
import fr.imie.gestionepicerie.dao.ArticleDAO;
import fr.imie.gestionepicerie.dao.LotDAO;
import fr.imie.gestionepicerie.dao.PanierDAO;
import fr.imie.gestionepicerie.exception.BusinessException;
import fr.imie.gestionepicerie.service.EpicerieService;
import fr.imie.gestionepicerie.view.Fenetre;

public class GestionEpicerie {
    
    public static void main(String[] args) throws BusinessException {
    	ToolTipManager.sharedInstance().setInitialDelay(1000);
    	ArticleDAO articleDAO = new ArticleDAO();
		LotDAO lotDAO = new LotDAO();
		PanierDAO panierDAO = new PanierDAO();
		EpicerieService epicerieService = new EpicerieService();
		epicerieService.setArticleDAO(articleDAO);
		epicerieService.setLotDAO(lotDAO);
		epicerieService.setPanierDAO(panierDAO);
		EpicerieController.getInstance().setEpicerieService(epicerieService);
        Fenetre fenetre = new Fenetre();
		fenetre.setVisible(true);
        
    }
}
