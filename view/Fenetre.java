package fr.imie.gestionepicerie.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {

	private StockPanel stock;  
	private InfoPanel info;  
	private VentePanel vente;  

	/**
	 * constructeur
	 */
	public Fenetre() {
		super("Gestion Epicerie");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// DO_NOTHING_ON_CLOSE

		this.setSize(900, 600);
		this.getContentPane().setLayout(new FlowLayout());
		makeMenu();
		stock = new StockPanel();
		info = new InfoPanel();
		vente = new VentePanel();

		info.build();
		vente.build();

		this.getContentPane().add(stock);
		this.refresh();

	}

	// ****************************** Menu  ******************************************
	

	public void makeMenu() {
		JMenuBar menu = new JMenuBar();
		// stock
		JMenuItem stockMenu = new JMenuItem("Stock");
		stockMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCenterPanel(stock); // showStock();
			}
		});
		menu.add(stockMenu);

		// infos
		JMenuItem infosMenu = new JMenuItem("Infos");
		infosMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCenterPanel(info); // showInfos();
			}
		});
		menu.add(infosMenu);

		// vente
		JMenuItem venteMenu = new JMenuItem("Vente");
		venteMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCenterPanel(vente); // showVente();
			}
		});
		menu.add(venteMenu);

		this.setJMenuBar(menu);
	}

	public void setCenterPanel(JPanel panel) {
		this.getContentPane().remove(0);
		this.getContentPane().add(panel);
		this.refresh();
	}

	public void refresh() {
		this.revalidate();
		this.repaint();
	}

	// *********************** dialogueConfirmation *******************************
	
	public void dispose() {
		DialogueConfirmation dialog = new DialogueConfirmation(Fenetre.this) {
			@Override
			public void onConfirm() {
				Fenetre.super.dispose();
			}
		};
		dialog.build();
		dialog.center();
		dialog.setVisible(true);
	}
}
