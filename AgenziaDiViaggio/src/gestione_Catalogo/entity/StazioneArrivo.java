/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.entity;

import java.io.Serializable;

public class StazioneArrivo extends ElementoCatalogo implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	//Costruttore
	public StazioneArrivo(IDEsterno idEsternoElemento, IDEsterno idEsternoViaggio){
		super(idEsternoElemento, idEsternoViaggio);
		}
	
	public StazioneArrivo(IDEsterno idEsternoElemento, IDEsterno idEsternoViaggio, Indice indice){
		super(idEsternoElemento, idEsternoViaggio, indice);
	}
	
	
	
	//ridefinisco il metodo equals
	public boolean equals(Object altroObject){
				
		// verifico se sono lo stesso oggetto
		if (this == altroObject) return true;
						
		// verifico se il parametro implicito � null
		if (altroObject == null) return false;
						
		//verifico se le classi non coincidono
		if (getClass() != altroObject.getClass()) return false;
						
		//ok, ora so che altroOggetto � un elemento non nullo, per cui faccio i confronti tra attributi
						
		StazioneArrivo nuovoElemento = (StazioneArrivo) altroObject;
						
		return (this.idEsternoElemento.equals(nuovoElemento.idEsternoElemento));  //devo ridefinire equals anche per IDEsterno (equals in profondita')
	}

}
