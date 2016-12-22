package fr.imie.gestionepicerie.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

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
import fr.imie.gestionepicerie.model.ListeVentePanierModel;



public class PanierPanel extends JPanel{

	private VentePanel parent;
	private JLabel idPanier;
	private JLabel total;
	private JButton supprimer;
	private JButton valider;
	private JButton annuler;
	private JTable tableau;
	private PanierModel panierModel;
	private Integer selected;
	private JPanel panierContainer;
	private ArticleModel article;

	public PanierPanel(VentePanel parent){
		this.parent = parent;
        
        panierContainer = new JPanel();
		panierContainer.setBorder(BorderFactory.createTitledBorder("Panier"));
        panierContainer.setLayout(new BoxLayout(panierContainer, BoxLayout.Y_AXIS));
        this.add( panierContainer );
        
        panierContainer.add( new JLabel("ID du panier : ") );
        idPanier = new JLabel("");
        panierContainer.add( idPanier );
        
        supprimer = new JButton("Supprimer l'article sélectionné");
        panierContainer.add( supprimer );
        
        tableau = new JTable();
        JScrollPane tableauContainer = new JScrollPane(tableau);
        tableauContainer.setPreferredSize(new Dimension(160,100));
        panierContainer.add( tableauContainer );
        
        JPanel lastLine = new JPanel();
        panierContainer.add( lastLine );
        lastLine.add( new JLabel("Total : ") );
        total = new JLabel("");
        lastLine.add( total );
        valider = new JButton("Validation et Paiement");
        lastLine.add( valider );
        annuler = new JButton("Annuler");
        lastLine.add( annuler );
        
		supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EpicerieController.getInstance().delArticle(article.getReference());
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
		tableau.setModel(new ListeVentePanierModel(panier.getVentes()));
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
