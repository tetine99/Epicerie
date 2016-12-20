package fr.imie.gestionepicerie.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.imie.gestionepicerie.controller.EpicerieController;
import fr.imie.gestionepicerie.exception.BusinessException;
import fr.imie.gestionepicerie.model.ListeArticleCaisseModel;
import fr.imie.gestionepicerie.model.ListeArticleModel;
import fr.imie.gestionepicerie.model.ListePanierModel;


@SuppressWarnings("serial")
public class VentePanel extends JPanel{
	//*********************** attributs ****************************************
	private String selected = "";
	private JTable tableau = new JTable();
	private JTextField searchField = new JTextField();
	private JComboBox<String> choixType = new JComboBox<>();
	private ListeArticleModel listeArticleModel;
	private JPanel formContainer = new JPanel();
	
	//***************************	*******************************************
	public void build(){
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel central = new JPanel();
        central.setLayout(new BoxLayout(central,BoxLayout.Y_AXIS));
        
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new BoxLayout(toolbar,BoxLayout.X_AXIS));
        toolbar.add( new JLabel("Recherche par référence : ") );
        toolbar.add( searchField );
        
        choixType.addItem("Article");
		choixType.addItem("Panier");
		ItemListener itemListener = new ItemListener() {
			  public void itemStateChanged(ItemEvent itemEvent) {
				String selected = (String) itemEvent.getItemSelectable().getSelectedObjects()[0];
				
			  }
			};
		choixType.addItemListener(itemListener);
	    toolbar.add( choixType );	
		
        JButton  rechercher = new JButton("Rechercher");
		rechercher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				venteTableau();
			}
		});
		toolbar.add(rechercher);
        central.add(toolbar);
        central.add(new JScrollPane(tableau));
        this.add(central);
        
        venteTableau();
            
        formContainer.add(new JPanel());
        this.add(formContainer);
        
       
	}

	//********************* afficher la liste des ventes ****************************
	public void updateTableau(){
		if (selected.equals("Article")){
			venteTableau();
		}
		else if (selected.equals("Panier")){
			panierTableau();
		}
	}
	
	//****************************** vente ***************************************
	public void venteTableau(){
		try{
			tableau.setModel(
				new ListeArticleCaisseModel(
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
	
	public void venteTableauFromSearch(String ref){
		try{
			listeArticleModel = new ListeArticleModel(
				EpicerieController.getInstance().getArticlesByRef(ref)
			);
			tableau.setModel(listeArticleModel);
			refresh();
		//}catch (BusinessException e){
			//onError(e);
		}catch (Exception e){
			onError(e);
		}
	}
	
	public void updateVenteTableau(){
		String input = searchField.getText();
		if(input.equals("")){
			venteTableau();
		}
		else{
			venteTableauFromSearch(input);
		}
		refresh();
	}
	
	//****************************** panier ***************************************
	public void panierTableau(int id){
		try{
			tableau.setModel(
				new ListePanierModel(
					EpicerieController.getInstance().getPanierById(id);
				)
			);
			refresh();
//		}catch (BusinessException e){
//			onError(e);
		}catch (Exception e){
			onError(e);
		}
	}
	
	
	
	//******************************************************************
	public void refresh(){
        this.revalidate();
		this.repaint();
    } 
	
	private void onError(BusinessException e){
		JOptionPane.showMessageDialog(this, "Erreur : BusinessException dans VentePanel");
	}
	
	private void onError(Exception e){
		//~ logger.error(e.getMessage(),e);
		JOptionPane.showMessageDialog(this, "Erreur : Exception dans VentePanel : "+e.getMessage());
	}

}
