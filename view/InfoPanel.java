package fr.imie.gestionepicerie.view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.imie.gestionepicerie.controller.EpicerieController;
import fr.imie.gestionepicerie.exception.BusinessException;
import fr.imie.gestionepicerie.model.ListeArticleModel;
import fr.imie.gestionepicerie.model.ListeInfoModel;
import fr.imie.gestionepicerie.model.ListeVenteModel;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel{
	
	private JTable tableau1 = new JTable();
	private JTable tableau2 = new JTable();
	private JTable tableau3 = new JTable();
	
	public void build() {
//		this.add(new JScrollPane(tableau1));
//		this.add(new JScrollPane (tableau2));
		this.add(new JScrollPane(tableau3));
//		infoTableauVente();
		infoTableauRecette();
	}
	
	//************************ liste vente *******************************
	public void infoTableauVente(){
		try{
			tableau1.setModel(
				new ListeVenteModel(
					EpicerieController.getInstance().getListVente()
				)
			);
			refresh();
//		}catch (BusinessException e){
//			onError(e);
		}catch (Exception e){
			onError(e);
		}
	}
	
	//****************************** liste article *****************************
	public void infoTableauArticle(){
		try{
			tableau2.setModel(
				new ListeArticleModel(
					EpicerieController.getInstance().getListArticles()
				)
			);
			refresh();
//		}catch (BusinessException e){
//			onError(e);
		}catch (Exception e){
			onError(e);
		}
	}
	
	//****************************** recette *****************************
		public void infoTableauRecette(){
			try{
				tableau3.setModel(
					new ListeInfoModel(
						EpicerieController.getInstance().getListArticles()
					)
				);
				refresh();
//			}catch (BusinessException e){
//				onError(e);
			}catch (Exception e){
				onError(e);
			}
		}
	
	public void refresh(){
        this.revalidate();
		this.repaint();
    } 
	
	
	//*************************** recette **************************************
	
	@SuppressWarnings("unused")
	private void onError(BusinessException e){
		JOptionPane.showMessageDialog(this, "Erreur : BusinessException dans StockPanel");
	}
	
	private void onError(Exception e){
		//~ logger.error(e.getMessage(),e);
		JOptionPane.showMessageDialog(this, "Erreur : Exception dans StockPanel : "+e.getMessage());
	}
}
