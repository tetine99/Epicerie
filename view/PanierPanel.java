package fr.imie.gestionepicerie.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.imie.gestionepicerie.controller.EpicerieController;
import fr.imie.gestionepicerie.model.ArticleModel;
import fr.imie.gestionepicerie.model.ListeArticleCaisseModel;
import fr.imie.gestionepicerie.model.ListeVenteModel;
import fr.imie.gestionepicerie.model.PanierModel;
import fr.imie.gestionepicerie.model.VenteModel;



public class PanierPanel extends JPanel{

	private  VentePanel parent;
	private JLabel idPanier = new JLabel("ID du panier : ");
	private JLabel total = new JLabel("Total : ");
	private JButton supprimer = new JButton("Supprimer l'article sélectionné");
	private JButton valider = new JButton("Validation et Paiement");
	private JButton annuler = new JButton("Annuler");
	private JTable tableau = new JTable();
	private PanierModel panierModel = new PanierModel();
	private Integer selected;
	private JPanel panierContainer;
	private ArticleModel article;

	public PanierPanel(VentePanel parent){
		this.parent = parent;
		panierContainer = new JPanel();
		panierContainer.setLayout(new BoxLayout(panierContainer, BoxLayout.Y_AXIS));
		panierContainer.setBorder(BorderFactory.createTitledBorder("Panier"));
		JPanel id = new JPanel();
		id.add(idPanier);
		this.add(id);

		JPanel supprimerArticle = new JPanel();
		supprimerArticle.add(supprimer);
		this.add(supprimerArticle);
		this.add(new JScrollPane(tableau));


		JPanel field = new JPanel();
		field.add(total);
		field.add(valider);
		field.add(annuler);
		this.add(field);
		supprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EpicerieController.getInstance().delArticle(String.valueOf(tableau.getRowCount()));

			}
		});
		valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});

		annuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tableau.setModel(new ListeArticleCaisseModel());

			}
		});


		ArticleModel a1 = new ArticleModel();
		VenteModel v1 = new VenteModel();
		a1.setReference("Y01");
		a1.setPrixVente(1.52);
		a1.setLibelle("pomme");
		v1.setQuantite(30);
		v1.getPrixTotal();
		v1.setArticle(a1);
		panierModel.addVente(v1);
		panierModel.addVente(v1);
		panierModel.addVente(v1);
		setModel(panierModel);

		tableau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				if (e.getClickCount() == 1) {

					final JTable target = (JTable) e.getSource();
					selected = target.getSelectedRow();


				}
			}
		});


	}
	public void setModel(PanierModel panier){
		idPanier.setText(String.valueOf(panier.getId()));
		total.setText(String.valueOf(panier.getTotal()));
		tableau.setModel(new ListeVenteModel(panier.getVentes()));
		selected = null;
	}
	public static void main(String[] args) {
		JFrame fenetre = new JFrame();
		VentePanel parent = null;
		PanierPanel panier = new PanierPanel(parent);
		fenetre.add(panier);
		fenetre.setVisible(true);
	}

}
