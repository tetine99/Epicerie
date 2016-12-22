package fr.imie.gestionepicerie.view;

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
import java.awt.Component;
import fr.imie.gestionepicerie.controller.EpicerieController;
import fr.imie.gestionepicerie.exception.BusinessException;
import fr.imie.gestionepicerie.model.ArticleModel;
import fr.imie.gestionepicerie.model.LotModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

@SuppressWarnings("serial")
public class LotFormPanel extends JPanel {
	private JTextField quantite;
	private JTextField refArticle;
	private JPanel buttonPane;
	private JButton nouveau;
	private JButton enregistrer;
	private JButton supprimer;
	private StockPanel parent;
	private LotModel lotModel;
	private boolean bool = false;
	private int id = 0;
	private SimpleDateFormat dateFormat;
	private JDatePickerImpl datePicker;
	private JDatePickerImpl datePicker2;

	public LotFormPanel(final StockPanel parent) {
        this.parent = parent;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBorder(BorderFactory.createTitledBorder("Formulaire lot"));
        
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        

		datePicker = addDateField(2016, 11, 20);
        addField( "Date d'achat : ", datePicker );
        datePicker2 = addDateField(2016, 11, 20);
        addField( "Date de Péremption : ", datePicker2 );
		quantite = new JTextField(3);
        quantite.setToolTipText("Quantité de l'article dans le lot");
        addField( "Quantité : ", quantite );
		refArticle = new JTextField(3);
        refArticle.setToolTipText("Reference de l'article contenu dans le lot ");
        addField( "Référence de l'article : ", refArticle );

		nouveau = new JButton("Nouveau");
        nouveau.setToolTipText("réinitialise les champs ");
		enregistrer = new JButton("Enregistrer");
        enregistrer.setToolTipText("Si le lot existe déja il sera modifier sinon il sera crée ");
		supprimer = new JButton("Suppimer");
        supprimer.setToolTipText("Supprimer un lot");
        buttonPane = new JPanel();
        buttonPane.add(nouveau);
		buttonPane.add(enregistrer);
		buttonPane.add(supprimer);
        this.add(buttonPane);
        
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

					Date selectedDate = (Date) datePicker.getModel().getValue();
					Date selectedDate2 = (Date) datePicker2.getModel().getValue();

					lot.setDateAchat(selectedDate);
					lot.setDatePeremption(selectedDate2);

					lot.setQuantite(Double.parseDouble(quantite.getText()));

					if (bool == false) {

						EpicerieController.getInstance().addLot(lot);
						parent.updateTable();
					}

					else if (bool == true) {
						lot.setId(id);
						EpicerieController.getInstance().modifLot(lot);
						parent.updateTable();
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

				EpicerieController.getInstance().delLot(lotModel.getId());
				parent.updateTable();

			}
		});



		
        
        reset();
	}
    
    /**
     * objet permettant la selection de la date via un outils graphique
     */
    public JDatePickerImpl addDateField(int y, int m, int d){
		UtilDateModel model = new UtilDateModel();
		model.setDate(y, m, d);
		model.setSelected(true);
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
        return new JDatePickerImpl(datePanel);
    }
    
    public void addField(String text, Component widget){
		JPanel field = new JPanel();
		field.add(new JLabel(text));
		field.add(widget);
		this.add(field);
    }


	public void reset() {
		refArticle.setText("");
		quantite.setText("");

	}

	/**
	 * Méthode pour griser les champs de saisie
	 * 
	 * @param bool
	 */
	public void setModeModif(boolean bool) {
		datePicker.setEnabled(bool);
		refArticle.setEnabled(bool);

	}

	/**
	 * Méthode pour récupérer le lot sélectionné dans le tableau
	 * 
	 * @param LotModel
	 */
	public void setModel(LotModel lot) {
		lotModel = lot;

		datePicker.getJFormattedTextField().setText(dateFormat.format(lot.getDateAchat()));
		datePicker2.getJFormattedTextField().setText(dateFormat.format(lot.getDatePeremption()));
		quantite.setText(String.valueOf(lot.getQuantite()));
		refArticle.setText(lot.getArticle().getReference());
		id = lot.getId();
		System.out.println("LotForm : " + lot);
		setModeModif(false);
		bool = true;

	}

	public void refresh() {
		StockPanel panel = new StockPanel();
		panel.updateTable();
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
