package fr.imie.gestionepicerie.view;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.imie.gestionepicerie.model.PanierModel;
import fr.imie.gestionepicerie.model.VenteModel;
/**
 * 
 * classe qui créer une popup de confirmation
 *
 */
@SuppressWarnings("serial")
public class TicketCaisse extends JDialog {
	
	// attribut
	private JFrame parent;
    private JLabel titre;
    private JPanel articles;
    private JLabel total;
    private PanierModel panierModel;

	// constructeur
	public TicketCaisse(JFrame fenetre) {
		super(fenetre, true);
		this.parent = fenetre;
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.titre = new JLabel("Epicerie");
		this.titre.setAlignmentY(JComponent.CENTER_ALIGNMENT);
		this.titre.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		this.getContentPane().add(titre);
        
        this.articles = new JPanel();
        this.articles.setLayout(new BoxLayout(this.articles, BoxLayout.Y_AXIS));
        this.getContentPane().add(articles);
        
        JPanel lastLine = new JPanel();
        lastLine.add(new JLabel("Total : "));
        this.total = new JLabel("");
        lastLine.add(this.total);
        this.getContentPane().add(lastLine);
        
		this.pack();
	}

	private void addArticle(VenteModel venteModel) {
		JPanel panel = new JPanel();
        panel.add( new JLabel( String.valueOf(venteModel.getArticle().getLibelle()) +" .... " ) );
        panel.add( new JLabel( String.valueOf(venteModel.getPrixTotal()) +" €" ) );
		this.articles.add(panel);
	}

    public void setModel(PanierModel panierModel){
        if (this.panierModel!=null){
            for(int i=this.panierModel.getNombreArticles();i>0;i--){
                this.articles.remove(0);
            }
        }
        this.panierModel = panierModel;
        for(VenteModel v : this.panierModel.getVentes()){
            addArticle(v);
        }
        this.total.setText( String.valueOf(this.panierModel.getTotal())+" €" );
        this.pack();
        this.refresh();
        this.center();
        this.setVisible(true);
        
    }
    
	public void onConfirm() {}

	public void center() {
		Point origin = parent.getLocation();
		int x = (int) (origin.getX() + parent.getWidth() / 2 - this.getWidth() / 2);
		int y = (int) (origin.getY() + parent.getHeight() / 2 - this.getHeight() / 2);
		this.setLocation(x, y);
	}

    public void refresh() {
		this.revalidate();
		this.repaint();
	}

}
