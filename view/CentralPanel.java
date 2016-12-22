package fr.imie.gestionepicerie.view;

import fr.imie.gestionepicerie.model.ListeArticleModel;
import fr.imie.gestionepicerie.model.ListeLotModel;
import fr.imie.gestionepicerie.controller.EpicerieController;
import fr.imie.gestionepicerie.exception.BusinessException;
import javax.swing.*;
import java.util.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public abstract class CentralPanel extends JPanel {
    
    private JPanel leftPart;
    protected JPanel rightPart;
    private JPanel toolbar;
    private JComboBox<String> partChoice;
    private JTextField searchField;
    private JButton searchButton;
    protected JTable table;
    protected JPanel tableContainer;
    protected String selectedPart;
    private ArrayList<String> partsList;
    
    
    public CentralPanel() {
        partsList = new ArrayList();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        leftPart = new JPanel();
        leftPart.setLayout(new BoxLayout(leftPart, BoxLayout.Y_AXIS));
        toolbar = new JPanel();
		searchField = new JTextField(20);
		partChoice = new JComboBox<>();
        searchButton = new JButton("Rechercher");
        tableContainer = new JPanel();
        table = new JTable();
        rightPart = new JPanel();
        rightPart.setLayout(new BoxLayout(rightPart, BoxLayout.Y_AXIS));
        
        
        toolbar.add( partChoice );
        toolbar.add( searchField );
        toolbar.add( searchButton );
        tableContainer.add( new JScrollPane( table ) );
        leftPart.add( toolbar );
        leftPart.add( tableContainer );
        this.add( leftPart );
        this.add( rightPart );
        
        
        ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				changeSelectedPart( (String) itemEvent.getItemSelectable().getSelectedObjects()[0] );
			}
		};
		partChoice.addItemListener(itemListener);
        
        searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTableFromSearch();
			}
		});
        
        table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				if (e.getClickCount() == 1) {
					final JTable target = (JTable) e.getSource();
					onSelectRow( target.getSelectedRow());
				}
			}
		});
        
    }

    public abstract void onChangePart();
    
    public abstract void onSelectRow(int row);
    
    public abstract void updateTableFromRef(String ref);
    
    public abstract void updateTable();
    
    public void addPart(String part){
        partsList.add( part );
        partChoice.addItem( part );
    }
    
    public void changeSelectedPart(String part) {
		if (!part.equals(selectedPart)) {
			selectedPart = part;
            onChangePart();
        }
    }

    public void updateTableFromSearch() {
		String input = searchField.getText();
		if (input.equals("")) {
			updateTable();
		} else {
			updateTableFromRef(input);
		}
		refresh();
	}
    
	public void refresh() {
		this.revalidate();
		this.repaint();
	}
	
}