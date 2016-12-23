package fr.imie.gestionepicerie.model;

import java.util.ArrayList;
import java.util.Date;

public class PanierModel {

	private int id;
    private Date dateModification;
    private ArrayList<VenteModel> ventes;

	public PanierModel(){
		this.ventes = new ArrayList<VenteModel>();
	}
    
	public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}

    public Date getDateModification() {
		return dateModification;
	}
    
	public void setDateModification(Date date) {
		this.dateModification = date;
	}
    
	public ArrayList<VenteModel>getVentes(){
		return ventes;
	}
    
	public void addVente(VenteModel vente){
		ventes.add(vente);
	}
    
	public double getTotal() {
        double total = 0;
        for (VenteModel v : ventes){
            total += v.getPrixTotal();
        }
		return total;
	}
    
	public double getBenefice() {
        double total = 0;
        for (VenteModel v : ventes){
            total += v.getBenefice();
        }
		return total;
	}
    
    public int getNombreArticles(){
        return ventes.size();
    }
    
	@Override
	public String toString() {
		return "PanierModel [id= " + id + ", dateModification= " + dateModification +"]";
	}

}
