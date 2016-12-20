package fr.imie.gestionepicerie.view;

import fr.imie.gestionepicerie.model.ListeArticleModel;
import fr.imie.gestionepicerie.model.ListeLotModel;
import fr.imie.gestionepicerie.controller.EpicerieController;
import fr.imie.gestionepicerie.exception.BusinessException;
import javax.swing.*;

import java.awt.event.*;
import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class StockPanel extends JPanel {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(StockPanel.class);
	
	private ArticleFormPanel articleForm;
	private LotFormPanel lotForm;
	private ListeArticleModel listeArticleModel;
	private ListeLotModel listeLotModel;
	
	private String selected = "";
	private JTextField searchField; 
	private JComboBox<String> choixType; 
	private JTable tableau ; 
	private JPanel formContainer; 
	private JPanel tableauContainer; 

	public StockPanel() {
		
		searchField = new JTextField(3);
		choixType = new JComboBox<>();
		tableau = new JTable();
		
		formContainer = new JPanel();
		tableauContainer = new JPanel();
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		// ******************************** infobulles ****************************************************
		searchField.setToolTipText("recherche d'un article ou d'un lot en fonction de la réfèrence saisie ");
		//*************************************************************************************************
		JPanel central = new JPanel();
		central.setLayout(new BoxLayout(central, BoxLayout.Y_AXIS));
		JPanel toolbar = new JPanel();
		choixType.addItem("Article");
		choixType.addItem("Lot");

		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				String selected = (String) itemEvent.getItemSelectable().getSelectedObjects()[0];
				changeSelectedPart(selected);
			}
		};
		choixType.addItemListener(itemListener);
		toolbar.add(choixType);
		toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.X_AXIS));
		toolbar.add(new JLabel("Recherche par référence : "));
		toolbar.add(searchField);

		JButton rechercher = new JButton("Rechercher");
		rechercher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTableauFromSearch();
			}
		});
		toolbar.add(rechercher);

		central.add(toolbar);

		tableauContainer.add(new JScrollPane(tableau));

		central.add(tableauContainer);

		this.add(central);
		lotForm  = new LotFormPanel(this);
		articleForm = new  ArticleFormPanel(this);
		
		formContainer.add(new JPanel());
		this.add(formContainer);
		tableau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				if (e.getClickCount() == 1) {
					final JTable target = (JTable) e.getSource();
					final int row = target.getSelectedRow();
					StockPanel.this.changeSelectedForm(row);
				}
			}
		});
		changeSelectedPart("Article");
	}

	/**
	 * methode pour choisir entre la table des articles et des lots 
	 * @param part
	 */
	public void changeSelectedPart(String part) {
		if (!part.equals(selected)) {
			// ~ System.out.println(part);
			selected = part;
			updateTableau();
			if (part.equals("Article")) {
				tableauContainer.setBorder(BorderFactory.createTitledBorder("Tableau des Articles"));
				changeForm(articleForm);

			} else if (part.equals("Lot")) {
				tableauContainer.setBorder(BorderFactory.createTitledBorder("Tableau des Lots"));
				changeForm(lotForm);
			}
		}
	}
	// ******************************* methodes pour rafraichir ou mettre a jour les tableaux *********************************
	public void updateTableau() {
		if (selected.equals("Article")) {
			updateArticleTableau();
		} else if (selected.equals("Lot")) {
			updateLotTableau();
		}
	}

	public void updateTableauFromRef(String ref) {
		if (selected.equals("Article")) {
			updateArticleTableauFromRef(ref);
		} else if (selected.equals("Lot")) {
			updateLotTableauFromRef(ref);
		}
	}

	public void updateArticleTableau() {
		try {
			listeArticleModel = new ListeArticleModel(EpicerieController.getInstance().getListArticles());
			tableau.setModel(listeArticleModel);
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
			tableau.setModel(listeArticleModel);
			refresh();
			// }catch (BusinessException e){
			// onError(e);
		} catch (Exception e) {
			onError(e);
		}
	}

	public void updateLotTableau() {
		try {
			listeLotModel = new ListeLotModel(EpicerieController.getInstance().getListLots());
			tableau.setModel(listeLotModel);
			refresh();
			// }catch (BusinessException e){
			// onError(e);
		} catch (Exception e) {
			onError(e);
		}
	}

	public void updateLotTableauFromRef(String ref) {
		try {
			listeLotModel = new ListeLotModel(EpicerieController.getInstance().getLotsByRef(ref));
			tableau.setModel(listeLotModel);
			refresh();
			// }catch (BusinessException e){
			// onError(e);
		} catch (Exception e) {
			onError(e);
		}
	}

	public void updateTableauFromSearch() {
		String input = searchField.getText();
		if (input.equals("")) {
			updateTableau();
		} else {
			updateTableauFromRef(input);
		}
		refresh();
	}

	public void changeForm(JPanel form) {
		formContainer.remove(0);
		formContainer.add(form);
		refresh();
	}

	public void changeSelectedForm(int row) {
		if (selected.equals("Article")) {
			articleForm.setModel(listeArticleModel.getArticle(row));
		} else if (selected.equals("Lot")) {
			lotForm.setModel(listeLotModel.getLot(row));
		}
	}

	public void refresh() {
		this.revalidate();
		this.repaint();
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
