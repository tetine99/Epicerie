package fr.imie.gestionepicerie.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.imie.gestionepicerie.model.ArticleModel;
import fr.imie.gestionepicerie.model.PanierModel;
import fr.imie.gestionepicerie.model.ListeVentePanierModel;



public class PanierPanel extends JPanel{

	private VentePanel parent;
	private JLabel idPanier;
	private JLabel total;
	private JButton supprimer;
	private JButton valider;
	private JTable tableau;
	private PanierModel panierModel;
	private JPanel panierContainer;
	private ArticleModel article;
    private int selected;

	public PanierPanel(VentePanel parent){
		this.parent = parent;
        
        panierContainer = new JPanel();
		panierContainer.setBorder(BorderFactory.createTitledBorder("Panier"));
        panierContainer.setLayout(new BoxLayout(panierContainer, BoxLayout.Y_AXIS));
        this.add( panierContainer );
        
        JPanel firstLine = new JPanel();
        panierContainer.add(firstLine);
        firstLine.add(new JLabel("ID du panier"));
        idPanier = new JLabel("");
        firstLine.add(idPanier);
        supprimer = new JButton("Supprimer l'article sélectionné");
        firstLine.add(supprimer);
        
        tableau = new JTable();
        JScrollPane tableauContainer = new JScrollPane(tableau);
        tableauContainer.setPreferredSize(new Dimension(100,160));
        panierContainer.add( tableauContainer );
        
        JPanel lastLine = new JPanel();
        panierContainer.add( lastLine );
        lastLine.add( new JLabel("Total : ") );
        total = new JLabel("");
        lastLine.add( total );
        valider = new JButton("Paiement");
        lastLine.add( valider );
        
		supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
	                   parent.delVenteFromPanierByLineNumber(selected);
	                   parent.updateTable();
	               }
	                   catch(Exception e) {
	                   onError(e);	
	               }
	              }
	         });
        
		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.validPanier();
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
		selected = -1;
        refresh();
	}

    public void refresh() {
		this.revalidate();
		this.repaint();
	}
    private void onError(Exception e) {
    if (selected == -1 ) {
         JOptionPane.showMessageDialog(this, "Veuillez selectionnez une vente avant de la supprimer ");
    }
                   
    System.out.println(e.getMessage());
}	
	
}
