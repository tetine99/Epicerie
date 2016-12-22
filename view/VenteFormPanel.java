package fr.imie.gestionepicerie.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.text.SimpleDateFormat;
import fr.imie.gestionepicerie.model.ArticleModel;
import fr.imie.gestionepicerie.model.VenteModel;

public class VenteFormPanel extends JPanel{

	private VentePanel parent;
	private ArticleModel article;
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
        article = new ArticleModel();
        
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
        venteFormContainer.add(ajouter);
        
        this.add(venteFormContainer);
        
        ajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                //~ EpicerieController.getInstance().addArticle(article);
                reset();
                //~ parent.updateTable();
                
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
    
    public void setModel(VenteModel vente){
        referenceArticle.setText( vente.getArticle().getReference() );
        date.setText( dateFormat.format(vente.getDate()) );
        //~ quantite.setText( String.valueOf( vente.getQuantite() ) );
		uniteMesure.setText( vente.getArticle().getUniteMesure() );
    }

    public void reset() {
		quantite.setText("");
		uniteMesure.setText("");
	}

}

