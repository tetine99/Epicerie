package fr.imie.gestionepicerie.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.text.SimpleDateFormat;
import fr.imie.gestionepicerie.model.ArticleModel;
import fr.imie.gestionepicerie.model.VenteModel;

public class VenteFormPanel extends JPanel{
    
    private VenteModel venteModel;
	private VentePanel parent;
	private JTextField referenceArticle;
	private JTextField date;
	private JTextField quantite;
	private JLabel uniteMesure;
    private JButton ajouter;
    private SimpleDateFormat dateFormat;
    private JPanel venteFormContainer;
    
    public VenteFormPanel(VentePanel parent){
        this.parent = parent;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        this.venteModel = new VenteModel();
        
        venteFormContainer = new JPanel();
        venteFormContainer.setLayout(new BoxLayout(venteFormContainer, BoxLayout.Y_AXIS));
        venteFormContainer.setBorder(BorderFactory.createTitledBorder("Formulaire Vente"));
        referenceArticle =  new JTextField(10);
        referenceArticle.setEnabled(false);
        addField( "Référence de l'article : ", referenceArticle );
        date  = new JTextField(10);
        date.setEnabled(false);
        addField( "Date et heure : ", date );
        
        quantite  = new JTextField(10);
        uniteMesure = new JLabel("");
        JPanel lastLine = addField( "Quantité : ", quantite );
        lastLine.add( uniteMesure );
        
        ajouter = new JButton("Ajouter au panier");
        venteFormContainer.add( ajouter );
        
        this.add(venteFormContainer);
        
        ajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                try{
                	venteModel.setQuantite( Double.parseDouble(quantite.getText()) );
                    parent.addVenteToPanier( venteModel );
                    reset();
                }catch (Exception e ){
                	onError(e);
                }
				
                
			}
		});
        
    }
    
    public JPanel addField(String text, JTextField textField){
		JPanel field = new JPanel();
		field.add(new JLabel(text));
		field.add(textField);
		venteFormContainer.add(field);
        return field;
    }
    
    public void setModel(VenteModel venteModel){
        this.venteModel = venteModel;
        referenceArticle.setText( venteModel.getArticle().getReference() );
        date.setText( dateFormat.format( venteModel.getDate() ) );
        //~ quantite.setText( String.valueOf( vente.getQuantite() ) );
		uniteMesure.setText( venteModel.getArticle().getUniteMesure() );
    }

    public void reset() {
		quantite.setText("");
		uniteMesure.setText("");
        
	}	private void onError(Exception e) {
		
		switch (e.getMessage()) {
		case "empty String":
			JOptionPane.showMessageDialog(this, "Veuillez selectionnez un article et saisir la quantité ", "Warning" , JOptionPane.WARNING_MESSAGE);
			break;
		
		}
		
		System.out.println(e.getMessage());
	}

}

