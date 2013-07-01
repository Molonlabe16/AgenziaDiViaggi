package gestione_Catalogo.entity;

import gestione_Catalogo.exception.IDEsternoElementoException;

import java.util.TreeMap;



/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class MappaCatalogo extends TreeMap<String,ElementoCatalogo> {
	

	private static final long serialVersionUID = 1L;

	public MappaCatalogo(){
		super();
	}
	
	
	public ElementoIntermedio getElemento(String k) throws IDEsternoElementoException{
		if (!containsKey(k)){
			throw new IDEsternoElementoException("Elemento \""+k+"\" non presente.");
		}
		return (ElementoIntermedio) super.get(k);
	}
	
	public ElementoFinale getElementoFinale(String k) throws IDEsternoElementoException{
		if (!containsKey(k)){
			throw new IDEsternoElementoException("Elemento \""+k+"\" non presente.");
		}
		return (ElementoFinale) super.get(k);

	}
	
	public void aggiungiElemento(String k, ElementoCatalogo e){
		if(!containsKey(k))
			super.put(k, e);
	}
	
	
	public void rimuoviElemento(String k) throws IDEsternoElementoException{
		if(!containsKey(k)){
			throw new IDEsternoElementoException("Errore in rimozione. Elemento \""+k+"\" non presente.");
		}
		super.remove(k);
		
	}
	
	public boolean esistenzaElemento(ElementoCatalogo e){  // se in parametro gli passo un elemento
		return super.containsKey(e.getIDEsternoElemento());
	}
}
