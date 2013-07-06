package gestione_Catalogo.entity;

import java.util.Set;

import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.OffertaInesistenteException;


/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public abstract class ElementoCatalogo {
	
	//attributi di istanza
	private Integer ID;
	private IDEsternoElemento idEsternoElemento;

	
	//costruttori
	public ElementoCatalogo (IDEsternoElemento idEsternoElemento){
		this.idEsternoElemento = idEsternoElemento;		
		//this.setID(0);
	}
	
	public ElementoCatalogo(Integer ID, IDEsternoElemento idEsternoElemento){
		this.ID= ID;
		this.idEsternoElemento = idEsternoElemento;
	}
	
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		this.ID = iD;
	}

	public String getIDEsternoElemento() {
		return idEsternoElemento.toString();
	}
	
	
	public void print() {
		System.out.println(this.getClass().getSimpleName() + " " + this.getID().toString() + " " +  this.getIDEsternoElemento().toString());
		
	}
	
	public ElementoCatalogo getElemento(String k) throws IDEsternoElementoException{
		return null;
	}
	
	public void aggiungiElemento(String key, ElementoCatalogo value){
		
	}
	
	public void rimuoviElemento(String key) throws IDEsternoElementoException {
		
	}
	
	public Set<String> listaChiaviElementi(){
		return null;
	}
	
	
	public void aggiungiOfferta(Data key, Offerta value){
		
	}
		
	public void rimuoviOfferta(Data key) throws OffertaInesistenteException{
		
	}

	public Set<Data> listaChiaviOfferte(){
		return null;
	}
	
	
}	