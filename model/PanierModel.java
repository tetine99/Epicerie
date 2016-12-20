package fr.imie.gestionepicerie.model;

import java.util.ArrayList;

public class PanierModel {

	private int id;
	private double total;
	private double benefice;
	private ArrayList<VenteModel>ventes = new ArrayList<>();

	public PanierModel(){

	}

	public PanierModel(int id, double total, double benefice){
		this.id = id;
		this.total = total;
		this.benefice = benefice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getBenefice() {
		return benefice;
	}
	public void setBenefice(double benefice) {
		this.benefice = benefice;
	}
	public ArrayList<VenteModel>getVentes(){
		return ventes;
	}
	public void addVente(VenteModel vente){
		ventes.add(vente);
	}
	

	@Override
	public String toString() {
		return "PanierModel [id= " + id + ", total= " + total + ", bénéfice= " + benefice +"]";
	}

}
